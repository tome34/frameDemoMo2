package com.example.tome.module_shop_cart.base;

/**
 * @author tome
 * @date 2018/8/10  10:53
 * @describe ${数据回调}
 */
public interface INetResult<T> {
    /**
     * 处理数据前
     */
    void requestBefore(int flag);

    /**
     * 处理成功
     */
    void onSuccess(int flag, T baseBean);

    /**
     * 错误回调
     */
    void onError(String error, String code, int flag);

    /**
     * 耗时处理完成
     */
    void complete(int flag);
}
