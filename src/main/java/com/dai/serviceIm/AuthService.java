package com.dai.serviceIm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.dai.mapper.UserMapper;
import com.dai.pojo.SpringContextsUtil;
import com.dai.pojo.StatusCode;
import com.dai.pojo.User;
import com.dai.service.AuthInterface;

@Service
public class AuthService implements AuthInterface{
	@Override
	public int Login(String email, String password) {
		// TODO Auto-generated method stub
		UserMapper user = (UserMapper)SpringContextsUtil.getBean("userMapper");
		User my = null;
		try {
			my = user.selectByEmail(email);
		} catch (Exception e) {
			// TODO: handle exception
			return StatusCode.EmailNotVailed;
		}
		if(my == null){
			return StatusCode.EmailNotVailed;
		}
		if(my.getPassword().equals(password)){
			return StatusCode.GetYeah;
		} else{
			return StatusCode.PasswordNotVailed;
		}
	}
	
	@Override
	public boolean Logout() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int Register(String email, String password, String name) {
		// TODO Auto-generated method stub
		UserMapper userMapper = (UserMapper)SpringContextsUtil.getBean("userMapper");
		User my = null;
		try {
			my = userMapper.selectByEmail(email);
		} catch (Exception e) {
			// TODO: handle exception
			//return StatusCode.EmailNotVailed;
			e.printStackTrace();
		}
		if(my != null){
			return StatusCode.EmailHasUsed;
		}
		my = new User();
		my.setEmail(email);
		my.setName(name);
		my.setPassword(password);
		userMapper.insert(my);
		return StatusCode.GetYeah;
	}

}
