package com.example.tome.projectCore.bean;

/**
 * @Created by TOME .
 * @时间 2018/5/4 15:47
 * @描述 ${TODO}
 */

public class BaseResponse {

    public static final String SUCCESS = "0";

    private int code ;
    private String message ;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
