package com.ss.server.domain;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/3
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
public class AccountInfo {

    /**
     * 剩余流量
     */
    private float lastFlow;

    public float getLastFlow() {
        return lastFlow;
    }

    public void setLastFlow(float lastFlow) {
        this.lastFlow = lastFlow;
    }
}
