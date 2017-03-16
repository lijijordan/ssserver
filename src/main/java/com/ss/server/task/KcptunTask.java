package com.ss.server.task;

import com.ss.server.entity.Kcptun;
import com.ss.server.service.SSManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 检查设备的离线状态任务
 */
@Component("task")
public class KcptunTask {

    // 一个小时
    private static final long ONE_HOUR = 1000 * 60 * 60;
    private static final long ONE_MINUTE = 1000 * 60;

    private final Logger log = LoggerFactory.getLogger(KcptunTask.class);

    @Autowired
    private SSManager ssManager;

    /**
     * 60分钟执行一次
     */
    @Scheduled(fixedRate = ONE_HOUR)
    public void initKcptun() {
        ssManager.findAllKcptun().forEach((Kcptun kcptun) -> {
            log.info("启动kcptun进程 : {}", kcptun.getKcpPort());
            ssManager.executeProcess(kcptun);
        });
    }

}  