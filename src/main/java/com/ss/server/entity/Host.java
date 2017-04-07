package com.ss.server.entity;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/4/6
 * Time: 下午10:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Host extends BaseEntity {

    private String ip;

    private int connectedSum;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getConnectedSum() {
        return connectedSum;
    }

    public void setConnectedSum(int connectedSum) {
        this.connectedSum = connectedSum;
    }
}
