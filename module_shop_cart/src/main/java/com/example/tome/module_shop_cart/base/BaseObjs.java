package com.example.tome.module_shop_cart.base;

/**
 * List继承DataList,对象继承ObjectBean
 * Created by Administrator on 2017/12/12.
 */

public class BaseObjs<T> {
    /**
     * code : 000
     * message : 请求成功
     * data : {"customterType":"0","message":"登陆成功","hxUserName":"user_77_93_1_0","token":"","state":"0","hxPassword":"888888","businessType":"1","uuid":"","isKeFu":"0","suMap":{"uid":"77","busiCompName":"深圳市筷云股份有限公司","busiCompType":"0","state":"1","lastTime":"2017-12-12 15:23:14.0","pid":"0","aid":"93","busiCompLevel":"0","defaultUid":"77","busiCompNick":"chaichai","topBid":"57","userTel":"15820400825","account":"13800138000","userName":"13800138000","brid":"","bid":"57","defaultAid":"93"}}
     */

    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
