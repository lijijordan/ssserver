package com.ss.server.domain.in;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/4
 * Time: 上午10:56
 * To change this template use File | Settings | File Templates.
 */
public class UserInfo {

    private String name;
    private String key;
    private String email;
    private String phone;
    private String mac;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
}
