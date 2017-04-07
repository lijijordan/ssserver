package com.ss.server.domain;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/16
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
public class SSKeyRequest {


    private String keyHost;

    private int length;
    /**
     * 流量
     */
    private int flow;

    private String keyType;

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getKeyHost() {
        return keyHost;
    }

    public void setKeyHost(String keyHost) {
        this.keyHost = keyHost;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }
}
