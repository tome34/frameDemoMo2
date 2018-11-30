package com.example.welfare.module_welfare.api;

import com.example.tome.core.base.BaseObserver;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.net.HttpHelper;
import com.example.tome.core.net.common_callback.INetCallback;
import com.example.tome.core.util.L;
import com.example.tome.core.util.RxUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import io.reactivex.Observable;

/**
 * @Created by TOME .
 * @时间 2018/5/16 9:52
 * @描述 ${TODO}
 */

public class ModelService {

    private ModelService mModelService;
    private INetCallback mCallback;

    /**
     * 接口回调
     * @param <T>
     */
    public interface MethodSelect<T>{

        Observable<T> selectM(ApiService service);
    }

    public ModelService(ModelService modelService) {
        mModelService = modelService;

    }

    /**
     * 获取远程基本数据
     * 带进度条的方法
     * @param mView
     * @return
     */
    public static <T> BaseObserver<T> getRemoteData(boolean isShowHUD, IView mView, MethodSelect<T> select, INetCallback<T> callback) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(2)
                .create(ApiService.class))
                .compose(RxUtils.<T>rxSchedulerHelper())
                .subscribeWith(new BaseObserver<T>(mView, isShowHUD) {
                                   @Override
                                   public void onNext(T result) {
                                       L.d("获取数据", ":" + result);
                                       mView.showNormal();
                                           callback.onSuccess(result);

                                   }
                               }
                );
    }



    /**
     * 封装了list的刷新
     * @param mView
     * @param select
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> BaseObserver<T> getRemoteListData(IView mView, SmartRefreshLayout rlRefresh, MethodSelect<T> select, INetCallback<T> callback) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(2)
                .create(ApiService.class))
                .compose(RxUtils.<T>rxSchedulerHelper())
                .subscribeWith(new BaseObserver<T>(mView, rlRefresh) {
                                   @Override
                                   public void onNext(T result) {
                                       Logger.json("获取数据"+result.toString());
                                       mView.showNormal();
                                       callback.onSuccess(result);

                                   }
                               }
                );
    }


    /**
     * 下载图片
     * @param mView
     * @param select
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> BaseObserver<T> downloadFile(IView mView, MethodSelect<T> select, INetCallback<T> callback){
        return select.selectM(HttpHelper.getDefault(2)
                .create(ApiService.class))
                .compose(RxUtils.<T>rxSchedulerHelper())
                .subscribeWith(new BaseObserver<T>(mView, "正在保存...") {
                                   @Override
                                   public void onNext(T result) {
                                       L.d("获取数据", ":" + result);
                                       mView.showNormal();
                                      callback.onSuccess(result);
                                   }

                               }
                );

    }


}
