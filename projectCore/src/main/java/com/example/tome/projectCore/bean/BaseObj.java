package com.example.tome.projectCore.bean;

/**
 * @Created by TOME .
 * @时间 2018/5/31 16:36
 * @描述 ${TODO}
 */

public class BaseObj<T> {

    private String errorCode;
    private String errorMsg;
    private T data;

    public String getCode() {
        return errorCode;
    }

    public void setCode(String code) {
        this.errorCode = code;
    }

    public String getMessage() {
        return errorMsg;
    }

    public void setMessage(String message) {
        this.errorMsg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
