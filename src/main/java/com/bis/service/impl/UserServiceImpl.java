package com.bis.service.impl;

import com.bis.dao.UserInfoMapper;
import com.bis.dao.UserPassWordMapper;
import com.bis.entity.UserInfo;
import com.bis.entity.UserPassWord;
import com.bis.service.UserService;
import com.bis.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserPassWordMapper userPassWordMapper;

    @Override
    public UserModel getUserById(Integer id) {
        //调用userMapper获取对应的用户信息
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        if (userInfo == null) return null;
        UserPassWord userPassWord = userPassWordMapper.selectByUserId(userInfo.getId());

        return convertFromDataObject(userInfo, userPassWord);

//        convertFromDataObject()
        //
    }

    private UserModel convertFromDataObject(UserInfo userInfo, UserPassWord userPassWord){
        if(userInfo == null){
            return null;
        }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInfo,userModel);
        if(userPassWord!=null){
            userModel.setEncrptPassword(userPassWord.getEncrptPassword());
        }
        return userModel;
    }
}
