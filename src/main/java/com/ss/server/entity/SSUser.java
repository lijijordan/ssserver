package com.ss.server.entity;

import java.util.Date;

public class SSUser {
	
	private int ID;
	private String userName;
	
	private String email;
	private String password;
	
	/*
	 * 加密密码
	 */
	private String pass;
	
	private String plan;
	
	private int port;
	private boolean enable;
	
	private Date createTime;
	
	/*
	 * 总流量 对应字段'transfer_enable'
	 */
	private float transferEnable;
	
	/*
	 * 已经使用流量，对应字段'u'
	 */
	private float used;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public float getTransferEnable() {
		return transferEnable;
	}

	public void setTransferEnable(float transferEnable) {
		this.transferEnable = transferEnable;
	}

	public float getUsed() {
		return used;
	}

	public void setUsed(float used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return "SSUser{" +
				"ID=" + ID +
				", userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", pass='" + pass + '\'' +
				", plan='" + plan + '\'' +
				", port=" + port +
				", enable=" + enable +
				", createTime=" + createTime +
				", transferEnable=" + transferEnable +
				", used=" + used +
				'}';
	}
}
