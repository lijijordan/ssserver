package com.ss.server.domain.in;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/16
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public class ChargeRequest {

    private String key;
    private String mac;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
