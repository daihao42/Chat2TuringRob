package com.dai.service;

import javax.servlet.http.HttpSession;

import com.dai.pojo.User;

public interface SessionInterface {
	
	public boolean isLogin(HttpSession session);
	
	public void setSession(HttpSession session,User user);
	
	public void deleteSession(HttpSession session);
	
}
