package com.ss.server.dao;


import com.ss.server.entity.SSUser;

public interface SSUserDao {

	void save(SSUser user);
	
	SSUser get(String email);
	
	int getMaxPort();
}
