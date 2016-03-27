package com.dai.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.dai.pojo.StatusCode;
import com.dai.pojo.User;
import com.dai.serviceIm.AuthService;
import com.dai.serviceIm.SessionService;

@Controller
public class AuthController {
	
	@RequestMapping("/login")
	public String Login(){
		return "login";
	}
	
	@RequestMapping("/auth")
	public void Auth(@RequestParam(value="email") String email,
            @RequestParam(value="password")String password,PrintWriter pw,HttpSession session){
		//使用ajax验证登录
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-service.xml");
		AuthService auth = (AuthService)ctx.getBean("authService");
		SessionService sess = (SessionService)ctx.getBean("sessionService");
		int res = auth.Login(email, password);
		switch(res){
			case StatusCode.GetYeah: 
				User user = new  User();
				user.setEmail(email);
				pw.write("ok");sess.setSession(session, user);break;
			case StatusCode.EmailNotVailed: pw.write("email"); break;
			case StatusCode.PasswordNotVailed:pw.write("password");break;
			default:pw.write("wrong:500");
		}
		
	}
	
		
	
	@RequestMapping("logout")
	public String LoginOut(HttpSession session){
		//销毁session
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-service.xml");
		SessionService sess = (SessionService)ctx.getBean("sessionService");
		sess.deleteSession(session);
		return "redirect:login";
	}
	
	@RequestMapping("register")
	public String Register(){
		return "register";
	}
	
	@RequestMapping("/auth/register")
	public void AuthRegister(@RequestParam(value="email") String email,
            @RequestParam(value="password")String password,
            @RequestParam(value="name")String name,
            PrintWriter pw,HttpSession session){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-service.xml");
		AuthService auth = (AuthService)ctx.getBean("authService");
		SessionService sess = (SessionService)ctx.getBean("sessionService");
		int res = auth.Register(email, password, name);
		switch(res){
			case StatusCode.GetYeah:
				User user = new  User();
				user.setEmail(email);
				pw.write("ok");sess.setSession(session, user);break;
			case StatusCode.EmailHasUsed:pw.write("email"); break;
			default:pw.write("wrong:500");	
		}
	}
	

}
