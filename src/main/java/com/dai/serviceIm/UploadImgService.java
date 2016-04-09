package com.dai.serviceIm;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.dai.mapper.UploadImgMapper;
import com.dai.pojo.SpringContextsUtil;
import com.dai.pojo.UploadImg;
import com.dai.service.UploadImgInterface;

@Service
public class UploadImgService implements UploadImgInterface {

	@Override
	public void addUploadImg(String email, String path) {
		// TODO Auto-generated method stub
		UploadImgMapper uimg = (UploadImgMapper)SpringContextsUtil.getBean("uploadImgMapper");
		UploadImg uploadImg = new UploadImg();
		uploadImg.setImg(path);
		uploadImg.setEmail(email);
		uploadImg.setUpTime(new Date());
		uimg.insert(uploadImg);
	}
	
	@Override
	public List<UploadImg> getAllImg(String email) {
		// TODO Auto-generated method stub
		UploadImgMapper uimg = (UploadImgMapper)SpringContextsUtil.getBean("uploadImgMapper");
		try{
			return uimg.selectByEmail(email);
		} catch(Exception e){
			return null;
		}
	}
	
	@Override
	public void deleteUploadImg() {
		// TODO Auto-generated method stub
		
	}
	
}
