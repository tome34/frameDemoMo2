package com.example.tome.module_shop_mall.bean;

import com.example.tome.projectCore.bean.BaseResponse;

/**
 * @author quchao
 * @date 2018/2/26
 */

public class LoginResponse extends BaseResponse {

    private LoginBean data;

    public LoginBean getData() {
        return data;
    }

    public void setData(LoginBean data) {
        this.data = data;
    }
}
