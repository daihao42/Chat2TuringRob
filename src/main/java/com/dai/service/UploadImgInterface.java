package com.dai.service;

import java.util.List;

import com.dai.pojo.UploadImg;

public interface UploadImgInterface {

	public void addUploadImg(String email,String path);
	
	public void deleteUploadImg();
	
	public List<UploadImg> getAllImg(String email);
}
