package com.dai.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dai.pojo.DTalk;
import com.dai.serviceIm.SessionService;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String Index(HttpSession session){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("spring-service.xml");
		SessionService sess = (SessionService)ctx.getBean("sessionService");
		if(sess.isLogin(session)){
			return "index";
		}
		return "redirect:login";
	}

	@RequestMapping("test")
	public void Test(@RequestParam(value="say") String say,HttpServletResponse response){
		DTalk d = new DTalk();
		String res = null;
		//System.out.print(d.getAnswer(say));
		try{
			response.setContentType("text/xml;charset=utf-8");  
	        response.setHeader("Cache-Control", "no-cache");   
			PrintWriter pw=response.getWriter();
			try{
				res = d.getAnswer(say);
				//获取0-100的随机数
//				Random random = new Random();
//				//休眠100-10000毫秒模拟打字时间
//				long i = random.nextInt(10000)%(10000-100+1)+100;
//				//System.out.print(i);
//				Thread.sleep(i);
			} catch(Exception e){
				res = "。。。";
				e.printStackTrace();
			}
			pw.write(res);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
