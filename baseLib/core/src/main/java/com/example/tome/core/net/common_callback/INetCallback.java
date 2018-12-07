package com.example.tome.core.net.common_callback;


/**
 * @Created by TOME .
 * @时间 2018/5/31 15:54
 * @描述 ${网络请求成功回调}
 */
@FunctionalInterface
public interface INetCallback<T> {

   void onSuccess(T result);
}
