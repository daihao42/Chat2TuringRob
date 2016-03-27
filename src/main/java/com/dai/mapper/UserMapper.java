package com.dai.mapper;

import org.apache.ibatis.annotations.Select;

import com.dai.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    @Select("select * from user where email=#{email}")
    User selectByEmail(String email);
}