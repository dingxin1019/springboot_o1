package com.bis.controller;

import com.bis.controller.view.UserView;
import com.bis.error.BussinessException;
import com.bis.error.EmBussinessError;
import com.bis.response.CommonReturnType;
import com.bis.service.UserService;
import com.bis.service.model.UserModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController {

   private static Log log = LogFactory.getLog(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //用户获取otp短信的接口
    @RequestMapping("/getotp")
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telphone") String telPhone){
        HashMap<String, Object> map = new HashMap<>();
        if (telPhone.length() != 11){
            map.put("errcode",EmBussinessError.TELPHONE_ERROR.getErrorCode());
            map.put("errMsg",EmBussinessError.TELPHONE_ERROR.getErrMsg());
            return CommonReturnType.create(map,"fail");
        }
        //按照一定的规则生成otp验证码
        Random rn = new Random();
        int rncode = rn.nextInt(99999);
        rncode += 10000;
        String otpcode = String.valueOf(rncode);

        //将otp验证码同对应的客户的手机号关联起来,使用Httpsession方式绑定手机号与otpCode
        httpServletRequest.getSession().setAttribute(telPhone,otpcode);



        //将otp验证码通过短信的服务发送给对应的用户
        log.info("telPhone = "+telPhone +" & otpcode = "+ otpcode);

        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BussinessException {
        //调用service服务获取用户对象返回给前端
        UserModel userModel = userService.getUserById(id);

        //获取的对象不存在
        if(userModel == null){
            throw new BussinessException(EmBussinessError.USER_NOT_EXIST);
        }

        UserView userView = convertFromUserModel(userModel);

        //返回通用的对象
        return CommonReturnType.create(userView);
    }

    private UserView convertFromUserModel(UserModel userModel){
        UserView userView = new UserView();
        BeanUtils.copyProperties(userModel,userView);
        return userView;
    }


}
