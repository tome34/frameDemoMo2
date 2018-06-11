package com.example.welfare.module_welfare.api;

import com.example.tome.component_base.base.BaseObserver;
import com.example.tome.component_base.base.inter.BaseView;
import com.example.tome.component_base.net.HttpHelper;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.RxUtils;
import com.example.tome.component_base.util.T;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.component_data.bean.BaseResponse;
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

    public ModelService(ModelService modelService) {
        mModelService = modelService;

    }

    /**
     * 获取远程基本数据
     * 带进度条的方法
     * @param mView
     * @return
     */
    public static <T> BaseObserver<T> getRemoteData(boolean isShowHUD, BaseView mView, MethodSelect<T> select, INetCallback<T> callback) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(2)
                .create(ApiService.class))
                .compose(RxUtils.<T>rxSchedulerHelper())
                .subscribeWith(new BaseObserver<T>(mView, isShowHUD) {
                                   @Override
                                   public void onNext(T result) {
                                       L.d("获取数据", ":" + result);
                                    //   if (BaseResponse.SUCCESS.equals(result.getCode())) {
                                           callback.onSuccess(result);
                                      // } else {
                                       //    mView.showError(result.getMessage(), result.getCode());
                                     //  }
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
    public static <T> BaseObserver<T> getRemoteListData(BaseView mView, SmartRefreshLayout rlRefresh, MethodSelect<T> select, INetCallback<T> callback) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(2)
                .create(ApiService.class))
                .compose(RxUtils.<T>rxSchedulerHelper())
                .subscribeWith(new BaseObserver<T>(mView, rlRefresh,false) {
                                   @Override
                                   public void onNext(T result) {
                                       L.d("获取数据", ":" + result);
                                       callback.onSuccess(result);

                                   }
                               }
                );
    }


    public interface MethodSelect<T>{

         Observable<T> selectM(ApiService service);
    }


}
