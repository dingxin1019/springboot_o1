package com.bis.error;

//定义枚举类型
public enum EmBussinessError implements CommonError {

    //通用的错误类型00001开头
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),

    UNKOWM_ERROR(10002,"未知错误"),

    //1000开头用户信息相错误
    USER_NOT_EXIST(20001,"用户不存在"),

    TELPHONE_ERROR(30001,"telphone_error")
    ;

    EmBussinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;

    private String errMsg;

    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
      return this.errMsg;
    }

    @Override
    public CommonError setErrorMsg(String errorMsg) {
        this.errMsg = errorMsg;
        return this;
    }
}
