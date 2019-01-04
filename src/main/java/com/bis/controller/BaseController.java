package com.bis.controller;

import com.bis.error.BussinessException;
import com.bis.error.EmBussinessError;
import com.bis.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 解决程序中出现的异常
 */
public class BaseController {

    //解决为被controller吸收的异常  -- 定义exceptionHandler
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex){
        HashMap<String, Object> map = new HashMap<>();
        if(ex instanceof BussinessException){
            BussinessException bussinessException = (BussinessException) ex;
            map.put("errcode",bussinessException.getErrorCode());
            map.put("errMsg",bussinessException.getErrMsg());
        }else{
            map.put("errcode", EmBussinessError.UNKOWM_ERROR.getErrorCode());
            map.put("errMsg",EmBussinessError.UNKOWM_ERROR.getErrMsg());
        }
        return CommonReturnType.create(map,"fail");
//        CommonReturnType commonReturnType = new CommonReturnType();
//        commonReturnType.setStatus("fail");
//        commonReturnType.setData(map);
//        return commonReturnType;
    }

}
