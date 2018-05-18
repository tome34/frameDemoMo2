package com.example.tome.component_base.base;

import com.example.tome.component_base.util.T;

/**
 * 类描述    :数据回调
 * 类名称    : INetResult
 * 创建人    : ghy
 * 创建时间  : 2017/8/3 16:19
 * 修改人    :
 * 修改时间  :
 * 修改备注  :
 */
public interface INetResult {
    /**
     * 处理数据前
     */
    void requestBefore();

    /**
     * 处理成功
     */
    void onSuccess();

    /**
     * 错误回调
     */
    void onError(String error, String code, int flag);

//    /**
//     * 错误回调
//     */
//    void onError2(String error, String code, String state,int flag);

    /**
     * 耗时处理完成
     */
    void complete(int flag);
}
