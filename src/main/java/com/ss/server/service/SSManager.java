package com.ss.server.service;

import com.ss.server.common.Constant;
import com.ss.server.dao.SSUserDao;
import com.ss.server.dao.jpa.KcptunRepository;
import com.ss.server.dao.jpa.SSKeyRepository;
import com.ss.server.dao.jpa.UserConfigRepository;
import com.ss.server.domain.AccountInfo;
import com.ss.server.domain.User;
import com.ss.server.domain.UserConfigDto;
import com.ss.server.domain.in.ChargeRequest;
import com.ss.server.domain.in.UserInfo;
import com.ss.server.entity.Kcptun;
import com.ss.server.entity.SSKey;
import com.ss.server.entity.SSUser;
import com.ss.server.entity.UserConfig;
import com.ss.server.utils.CommandExecutor;
import com.ss.server.utils.KeyGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/2
 * Time: 下午9:31
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SSManager {

    /**
     * The constant TRAFFIC_UNIT_G.
     */
    public final static float TRAFFIC_UNIT_G = 1024 * 1024 * 1024;
    /**
     * The constant DEFAULT_FLOW.
     */
    private static final Logger log = LoggerFactory.getLogger(SSManager.class);

    @Autowired
    private SSUserDao ssUserDao;

    @Autowired
    private SSKeyRepository ssKeyRepository;

    @Autowired
    private KcptunRepository kcptunRepository;

    @Autowired
    private UserConfigRepository userConfigRepository;

    /**
     * Create user.
     *
     * @param user the user
     * @param flow the flow
     * @return the ss user
     */
    public SSUser createSSUser(User user, int flow) {
        log.info("创建SS用户：{}", user.toString());
        int port = this.getMaxPort();
        String pass = RandomStringUtils.randomNumeric(6);
        SSUser ss = new SSUser();
        ss.setEmail(user.getEmail());
        ss.setEnable(true);
        ss.setPass(pass);
        ss.setPassword(pass);
        ss.setPort(port);
        ss.setTransferEnable(flow * TRAFFIC_UNIT_G);
        ss.setUsed(0);
        ss.setUserName(user.getFirstName());
        ssUserDao.save(ss);
        return ss;
    }

    // 不安全，暂时使用
    // 这块逻辑可能有问题，出现port冲突的问题 2016-01-10
    private int getMaxPort() {
        // 数据量大以后可能有问题。
        return ssUserDao.getMaxPort() + 1;
        /*int port = INCREMENT_PORT.get();
        if(port == 0){
			port = this.ssUserDao.getMaxPort();
			// 当前最大值
			// 解决端口重复
			INCREMENT_PORT.set(port + 1);
		}
		return INCREMENT_PORT.incrementAndGet();*/
    }


    /**
     * Generate key string.
     *
     * @param keyLength the key length
     * @param host      the host
     * @return the string
     */
    public String generateKey(int keyLength, String host) {
        return this.generateKey(keyLength, host, Constant.DEFAULT_FLOW_5_GB);
    }

    /**
     * Generate key string.
     *
     * @param keyLength the key length
     * @param host      the host
     * @param flow      the flow
     * @return the string
     */
    public String generateKey(int keyLength, String host, int flow) {
        if (host.equals("")) {
            host = Constant.KCP_DEFAULT_HOST;
        }
        String key = KeyGenerator.make(keyLength);
        SSKey ssKey = new SSKey();
        ssKey.setKey(key);
        ssKey.setFlow(flow);
        ssKey.setKeyHost(host);
        ssKey.setKeyLength(keyLength);
        this.ssKeyRepository.save(ssKey);
        return ssKey.getKey();
    }


    private SSKey validateKey(String key) {
        SSKey ssKey = this.ssKeyRepository.findByKey(key);
        if (ssKey != null && !ssKey.isUsed()) {
            // 验证完成失效
            ssKey.setUpdateTime(new Date());
            ssKey.setUsed(true);
            this.ssKeyRepository.save(ssKey);
            return ssKey;
        } else {
            return null;
        }
    }


    /**
     * Gets config.
     *
     * @param userInfo the user info
     * @return the config
     */
    public UserConfigDto getConfig(UserInfo userInfo) {
        UserConfig userConfig = findConfigByMac(userInfo.getMac());
        if (userConfig != null) {
            log.info("用户获取配置信息:{}", userConfig.toString());
            UserConfigDto ssConfig = new UserConfigDto();
            BeanUtils.copyProperties(userConfig, ssConfig);
            return ssConfig;
        } else {
            // 验证秘钥
            SSKey ssKey = validateKey(userInfo.getKey());
            if (ssKey != null) {
                User user = new User();
                user.setEmail(userInfo.getMac());
                user.setFirstName(userInfo.getMac());
                SSUser ssUser = this.createSSUser(user, ssKey.getFlow());
                Kcptun kcptun = this.createKcpUser(ssUser.getPort(), ssKey.getKeyHost());
                this.executeProcess(kcptun);
                UserConfig ssConfig = this.saveUserConfig(userInfo, kcptun, ssUser);
                UserConfigDto userConfigDto = new UserConfigDto();
                BeanUtils.copyProperties(ssConfig, userConfigDto);
                return userConfigDto;
            } else {
                log.info("创建用户失败，验证码无效！");
                throw new RuntimeException("无效的验证码");
            }
        }
    }

    private Kcptun createKcpUser(int ssPort, String kcpHost) {
        Kcptun kcptun = new Kcptun();
        kcptun.setKcpPort(ssPort + Constant.KCPTUN_PORT_INCREASE);
        kcptun.setSsHost(Constant.SS_HOST);
        kcptun.setSsPort(ssPort);
        kcptun.setKcpHost(kcpHost);
        kcptun.setSsMode(Constant.SS_SEC_MODE_RC4_MD5);
        kcptun.setKcpMode(Constant.KCP_MODE);
        this.kcptunRepository.save(kcptun);
        return kcptun;
    }

    private UserConfig findConfigByMac(String mac) {
        return this.userConfigRepository.findByMac(mac);
    }


    private UserConfig saveUserConfig(UserInfo userInfo, Kcptun kcptun, SSUser ssUser) {
        log.info("保存用户配置信息！");
        UserConfig userConfig = new UserConfig();
        userConfig.setEmail(userInfo.getEmail());
        userConfig.setKcpHost(kcptun.getKcpHost());
        userConfig.setKcpMode(kcptun.getKcpMode());
        userConfig.setKcpPort(kcptun.getKcpPort());
        userConfig.setKey(userInfo.getKey());
        userConfig.setMac(userInfo.getMac());
        userConfig.setName(userInfo.getName());
        userConfig.setPhone(userInfo.getPhone());
        userConfig.setSsHost(kcptun.getSsHost());
        userConfig.setSsPassword(ssUser.getPassword());
        userConfig.setSsPort(ssUser.getPort());
        userConfig.setSsSecMode(kcptun.getSsMode());
        return this.userConfigRepository.save(userConfig);
    }

    /**
     * ./server_linux_386 -t 127.0.0.1:10896 -l :1980 -mode fast2
     *
     * @param kcptun
     */
    private void executeProcess(Kcptun kcptun) {
        log.info("创建KCP进程");
        String command = "./server_linux_386 -t " + kcptun.getKcpHost() + ":" + kcptun.getSsPort() + " -l :" + kcptun.getKcpPort() + " -mode fast2";
        CommandExecutor.executeCommand(command);
    }


    /**
     * Gets over flow by mac.
     *
     * @param mac the mac
     * @return the over flow by mac
     */
    public AccountInfo getOverFlowByMac(String mac) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setLastFlow(this.ssUserDao.getLastFlow(mac) / Constant.PER_GB);
        return accountInfo;
    }


    /**
     * Charge ss boolean.
     *
     * @param chargeRequest the charge request
     * @return the boolean
     */
    public boolean chargeSS(ChargeRequest chargeRequest) {
        UserConfig userConfig = findConfigByMac(chargeRequest.getMac());
        if (userConfig != null) {
            // 验证秘钥
            SSKey ssKey = validateKey(chargeRequest.getKey());
            if (ssKey == null) {
                throw new RuntimeException("秘钥验证失败");
            } else {
                // 增加流量操作
                ssUserDao.updateFlow(chargeRequest.getMac(), ssKey.getFlow() * Constant.PER_GB);
            }
            return true;
        } else {
            throw new RuntimeException("没有找到这个mac地址");
            // 验证秘钥
        }
    }
}
