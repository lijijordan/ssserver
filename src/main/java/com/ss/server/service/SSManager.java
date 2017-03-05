package com.ss.server.service;

import com.ss.server.common.Constant;
import com.ss.server.dao.SSUserDao;
import com.ss.server.dao.jpa.KcptunRepository;
import com.ss.server.dao.jpa.SSKeyRepository;
import com.ss.server.domain.SSConfig;
import com.ss.server.domain.User;
import com.ss.server.domain.in.UserInfo;
import com.ss.server.entity.Kcptun;
import com.ss.server.entity.SSKey;
import com.ss.server.entity.SSUser;
import com.ss.server.utils.CommandExecutor;
import com.ss.server.utils.KeyGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
// 5GB 默认
    public final static float DEFAULT_FLOW = 5;
    private static final Logger log = LoggerFactory.getLogger(SSManager.class);
    @Autowired
    private SSUserDao ssUserDao;

    @Autowired
    private SSKeyRepository ssKeyRepository;

    @Autowired
    private KcptunRepository kcptunRepository;

    /**
     * Create user.
     *
     * @param user the user
     */
    public SSUser createSSUser(User user) {
        // 创建ssuser
        int port = this.getMaxPort();
        String pass = RandomStringUtils.randomNumeric(6);
        SSUser ss = new SSUser();
        ss.setEmail(user.getEmail());
        ss.setEnable(true);
        ss.setPass(pass);
        ss.setPassword(pass);
        ss.setPort(port);
        ss.setTransferEnable(DEFAULT_FLOW * TRAFFIC_UNIT_G);
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
     * @return the string
     */
    public String generateKey(int keyLength) {
        String key = KeyGenerator.make(keyLength);
        SSKey ssKey = new SSKey();
        ssKey.setKey(key);
        ssKey.setKeyLength(keyLength);
        this.ssKeyRepository.save(ssKey);
        return ssKey.getKey();
    }


    private boolean validateKey(String key) {
        SSKey ssKey = this.ssKeyRepository.findByKey(key);
        if (ssKey != null && !ssKey.isUsed()) {
            // 验证完成失效
            ssKey.setUpdateTime(new Date());
            ssKey.setUsed(true);
            this.ssKeyRepository.save(ssKey);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Gets config.
     *
     * @return the config
     */
    @Transactional
    public SSConfig getConfig(UserInfo userInfo) {
        SSConfig ssConfig = new SSConfig();
        if (validateKey(userInfo.getKey())) {
            User user = new User();
            user.setEmail(userInfo.getEmail());
            user.setFirstName(userInfo.getName());
            SSUser ssUser = this.createSSUser(user);
            Kcptun kcptun = this.createKcpUser(ssUser);
            ssConfig.setKcpHost(kcptun.getKcpHost());
            ssConfig.setKcpMode(SSConfig.KCP_MODE);
            ssConfig.setKcpPort(kcptun.getKcpPort());
            ssConfig.setSsPassword(ssUser.getPassword());
            ssConfig.setSsPort(ssUser.getPort());
            ssConfig.setSsSecMode(SSConfig.SS_SEC_MODE_RC4_MD5);
        } else {
            log.info("创建用户失败，验证码无效！");
            throw new RuntimeException("无效的验证码");
        }
        return ssConfig;
    }

    private Kcptun createKcpUser(SSUser ssUser) {
        Kcptun kcptun = new Kcptun();
        kcptun.setKcpPort(ssUser.getPort() + Constant.KCPTUN_PORT_INCREASE);
        kcptun.setSsHost(Constant.SS_HOST);
        kcptun.setSsPort(ssUser.getPort());
        kcptun.setKcpHost("127.0.0.1");
        this.kcptunRepository.save(kcptun);
        this.executeProcess(kcptun);
        return kcptun;
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
}
