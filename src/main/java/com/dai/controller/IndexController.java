package com.dai.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

	@RequestMapping("/say")
	public void Say(@RequestParam(value="say") String say,HttpServletResponse response){
		DTalk d = new DTalk();
		String res = null;
		//System.out.print(d.getAnswer(say));
		try{
			response.setContentType("text/xml;charset=utf-8");  
	        response.setHeader("Cache-Control", "no-cache");   
			PrintWriter pw=response.getWriter();
			try{
				res = d.getAnswer(say);
				//��ȡ0-100�������
//				Random random = new Random();
//				//����100-10000����ģ�����ʱ��
//				long i = random.nextInt(10000)%(10000-100+1)+100;
//				//System.out.print(i);
//				Thread.sleep(i);
			} catch(Exception e){
				res = "������";
				e.printStackTrace();
			}
			pw.write(res);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * �ϴ�����
	 */
	@RequestMapping("/test")
	public String test(){
		return "upload";
	}
	
	
	/**
	 * �ϴ��ļ�
	 */
	@RequestMapping("/upload")
	public void Upload(HttpServletRequest request,     
            HttpServletResponse response){
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
        //ȡ��request�е������ļ���  
        Iterator<String> iter = multiRequest.getFileNames();
        while(iter.hasNext()){
        	MultipartFile file = multiRequest.getFile(iter.next());
        	if(file != null){  
                //ȡ�õ�ǰ�ϴ��ļ����ļ�����  
                String myFileName = file.getOriginalFilename();  
                String path = request.getSession().getServletContext().getRealPath("/")+"/include/img/";
                path = path + UUID.randomUUID() + "." + myFileName.split("\\.")[1];  
                File localFile = new File(path);  
                try {
					file.transferTo(localFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
	}
}
