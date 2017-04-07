package com.ss.server.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * a simple domain entity doubling as a DTO
 */
@Entity
@Table(name = "ss_key")
public class SSKey extends BaseEntity {

    public static final String KEY_TYPE_CHARGE = "Charge";
    public static final String KEY_TYPE_CREATE = "Create";

    @Column
    private int keyLength;

    @Column(name = "s_key")
    private String key;

    private String keyHost;
    /**
     * is Used by client
     */
    private boolean isUsed;

    /**
     * 流量
     */
    private int flow;

    private String base64Code;

    /**
     * Charge \ Create
     */
    private String keyType;

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getBase64Code() {
        return base64Code;
    }

    public void setBase64Code(String base64Code) {
        this.base64Code = base64Code;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public String getKeyHost() {
        return keyHost;
    }

    public void setKeyHost(String keyHost) {
        this.keyHost = keyHost;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(int keyLength) {
        this.keyLength = keyLength;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
