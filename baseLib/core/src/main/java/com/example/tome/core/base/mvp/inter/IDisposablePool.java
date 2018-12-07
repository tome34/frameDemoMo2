package com.example.tome.core.base.mvp.inter;

import io.reactivex.disposables.Disposable;

/**
 * Created by XQ Yang on 2017/9/6  17:15.
 * Description : 连接池
 */

public interface IDisposablePool {
    /**
     * rxjava管理订阅者
     * @param disposable
     */
    void addDisposable(Disposable disposable);

    /**
     * 丢弃连接 在view销毁时调用,取消订阅关系
     */
    void clearPool();

}
