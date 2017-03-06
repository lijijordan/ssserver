package com.ss.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/6
 * Time: 下午1:39
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ss_user_config")
public class UserConfig extends BaseEntity {

    private String kcpHost;

    @Column(unique = true)
    private int kcpPort;

    private String ssPassword;

    @Column(unique = true)
    private int ssPort;

    private String ssHost;

    /**
     * ss加密方式
     */
    private String ssSecMode;

    /**
     * kcp加速类型
     */
    private String kcpMode;

    private String name;

    @Column(name = "user_key")
    private String key;
    private String email;
    private String phone;

    @Column(name = "mac_address", unique = true)
    private String mac;

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

    public int getSsPort() {
        return ssPort;
    }

    public void setSsPort(int ssPort) {
        this.ssPort = ssPort;
    }

    public String getSsHost() {
        return ssHost;
    }

    public void setSsHost(String ssHost) {
        this.ssHost = ssHost;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "kcpHost='" + kcpHost + '\'' +
                ", kcpPort=" + kcpPort +
                ", ssPassword='" + ssPassword + '\'' +
                ", ssPort=" + ssPort +
                ", ssHost='" + ssHost + '\'' +
                ", ssSecMode='" + ssSecMode + '\'' +
                ", kcpMode='" + kcpMode + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
