package com.ss.server.domain;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/3
 * Time: 下午6:20
 * To change this template use File | Settings | File Templates.
 */
public class UserConfigDto {

    private String kcpHost;

    private int kcpPort;

    private String ssPassword;

    /**
     * ss加密方式
     */
    private String ssSecMode;

    /**
     * kcp加速类型
     */
    private String kcpMode;

    public String getKcpHost() {
        return kcpHost;
    }

    public void setKcpHost(String kcpHost) {
        this.kcpHost = kcpHost;
    }

    public int getKcpPort() {
        return kcpPort;
    }

    public void setKcpPort(int kcpPort) {
        this.kcpPort = kcpPort;
    }

    public String getSsPassword() {
        return ssPassword;
    }

    public void setSsPassword(String ssPassword) {
        this.ssPassword = ssPassword;
    }

    public String getSsSecMode() {
        return ssSecMode;
    }

    public void setSsSecMode(String ssSecMode) {
        this.ssSecMode = ssSecMode;
    }

    public String getKcpMode() {
        return kcpMode;
    }

    public void setKcpMode(String kcpMode) {
        this.kcpMode = kcpMode;
    }
}
