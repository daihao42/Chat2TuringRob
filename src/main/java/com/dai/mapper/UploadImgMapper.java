package com.dai.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.dai.pojo.UploadImg;

public interface UploadImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UploadImg record);

    int insertSelective(UploadImg record);

    UploadImg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UploadImg record);

    int updateByPrimaryKey(UploadImg record);
    
    @Select("select * from upload_img where email=#{email}")
    List<UploadImg> selectByEmail(String email);
}