package com.bis.dao;

import com.bis.entity.UserPassWord;

public interface UserPassWordMapper {

    UserPassWord selectByUserId(Integer userId);

    int deleteByPrimaryKey(Integer id);

    int insert(UserPassWord record);

    int insertSelective(UserPassWord record);

    UserPassWord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPassWord record);

    int updateByPrimaryKey(UserPassWord record);
}