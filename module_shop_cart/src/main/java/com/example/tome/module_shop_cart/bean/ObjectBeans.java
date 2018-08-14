package com.example.tome.module_shop_cart.bean;

import android.text.TextUtils;

/**
 * 基础对象
 * Created by Administrator on 2017/12/13.
 */

public class ObjectBeans {
    private String message;

    public String getMsg() {
        return TextUtils.isEmpty(msg) ? message : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
    private String state;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
