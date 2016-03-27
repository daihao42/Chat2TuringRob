package com.dai.serviceIm;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.dai.pojo.User;
import com.dai.service.SessionInterface;

@Service
public class SessionService implements SessionInterface {
	@Override
	public boolean isLogin(HttpSession session) {
		// TODO Auto-generated method stub
		if(session.getAttribute("user")!= null){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void setSession(HttpSession session, User user) {
		// TODO Auto-generated method stub
		user.setPassword("");
		session.setAttribute("user", user);
		
	}
	
	@Override
	public void deleteSession(HttpSession session) {
		// TODO Auto-generated method stub
		session.removeAttribute("user");
	}
}
