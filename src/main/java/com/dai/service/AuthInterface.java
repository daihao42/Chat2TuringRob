package com.dai.service;


public interface AuthInterface {
	
	public int Login(String email,String password);
	
	public int Register(String email,String password,String name);
	
	public boolean Logout();
}
