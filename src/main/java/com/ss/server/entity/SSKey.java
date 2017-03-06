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

    @Column
    private int keyLength;

    @Column(name = "s_key")
    private String key;

    private String keyHost;
    /**
     * is Used by client
     */
    private boolean isUsed;

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
