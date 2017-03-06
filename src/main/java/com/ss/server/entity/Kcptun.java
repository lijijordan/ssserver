package com.ss.server.entity;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/4
 * Time: 上午9:53
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Kcptun extends BaseEntity {

    private String ssHost;

    private int ssPort;

    private String kcpHost;
    private int kcpPort;

    public String getKcpHost() {
        return kcpHost;
    }

    public void setKcpHost(String kcpHost) {
        this.kcpHost = kcpHost;
    }

    public String getSsHost() {
        return ssHost;
    }

    public void setSsHost(String ssHost) {
        this.ssHost = ssHost;
    }

    public int getSsPort() {
        return ssPort;
    }

    public void setSsPort(int ssPort) {
        this.ssPort = ssPort;
    }

    public int getKcpPort() {
        return kcpPort;
    }

    public void setKcpPort(int kcpPort) {
        this.kcpPort = kcpPort;
    }

    @Override
    public String toString() {
        return "Kcptun{" +
                "ssHost='" + ssHost + '\'' +
                ", ssPort=" + ssPort +
                ", kcpHost='" + kcpHost + '\'' +
                ", kcpPort=" + kcpPort +
                '}';
    }
}
