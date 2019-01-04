package com.bis.error;

public interface CommonError {

    //错误码
    public int getErrorCode();

    //错误信息
    public String getErrMsg();

    //自定义error
    public CommonError setErrorMsg(String errorMsg);
}
