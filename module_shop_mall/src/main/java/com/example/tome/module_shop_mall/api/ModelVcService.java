package com.example.tome.module_shop_mall.api;

import com.example.tome.core.base.BaseObserver;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.net.HttpHelper;
import com.example.tome.core.net.common_callback.INetCallback;
import com.example.tome.core.util.L;
import com.example.tome.core.util.RxUtils;
import com.example.tome.projectCore.bean.BaseObj;
import com.example.tome.projectCore.bean.BaseResponse;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @Created by TOME .
 * @时间 2018/5/16 9:52
 * @描述 ${TODO}
 */

public class ModelVcService {

    private static CompositeDisposable compositeDisposable;
    private ModelVcService mModelService;
    private INetCallback mCallback;

    /**
     * 获取api的回调
     * @param <T>
     */
    public interface MethodSelect<T>{

        Observable<BaseObj<T>> selectM(ApiService service);
    }

    public ModelVcService(ModelVcService modelService) {
        mModelService = modelService;

    }
 /*   public static Observable<FeedArticleListResponse> getFeedArticleList(int pageNum) {
        //设置不同的BaseUrl
        Retrofit retrofit = HttpHelper.getDefault(1);
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService.getFeedArticleList(pageNum);
    }*

/*    public static BaseObserver<BaseObj<FeedArticleListData>> getRemoteData(int pageNum, MainContract.View mView, INetCallback callback) {
        //设置不同的BaseUrl
        return HttpHelper.getDefault(1)
                .create(ApiService.class)
                .getFeedArticleList(pageNum)
                .compose(RxUtils.<BaseObj<FeedArticleListData>>rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseObj<FeedArticleListData>>(mView) {
                                   @Override
                                   public void onNext(BaseObj<FeedArticleListData> feedArticleListData) {
                                       L.d("获取message", ":" + feedArticleListData.getMessage());
                                       if (feedArticleListData.getCode() == BaseResponse.SUCCESS) {
                                           callback.onSuccess();
                                       } else {
                                           mView.showError(feedArticleListData.getMessage(), feedArticleListData.getCode());
                                       }
                                   }
                               }
                );
    }


}*/




    /**
     * 获取远程基本数据 mvc模式
     * 带进度条的方法
     * @return
     * @param isShowHUD
     * @param mView
     * @param select
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> BaseObserver<BaseObj<T>> getRemoteData(boolean isShowHUD, IView mView, MethodSelect<T> select, INetCallback<T> callback) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(1)
                .create(ApiService.class))
                     .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
                     .subscribeWith(new BaseObserver<BaseObj<T>>(mView, isShowHUD) {
                                   @Override
                                   public void onNext(BaseObj<T> result) {
                                       L.d("获取message", ":" + result.getMessage());
                                       if (BaseResponse.SUCCESS.equals(result.getCode())) {
                                           mView.showNormal();
                                           callback.onSuccess(result.getData());
                                       } else {
                                           mView.showError(result.getMessage(), result.getCode());
                                       }
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
    public static <T> BaseObserver<BaseObj<T>> getRemoteListData(IView mView, SmartRefreshLayout rlRefresh, MethodSelect<T> select, INetCallback<T> callback) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(1)
                .create(ApiService.class))
                .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseObj<T>>(mView, rlRefresh) {
                                   @Override
                                   public void onNext(BaseObj<T> result) {
                                       L.d("获取message", ":" + result.getMessage());
                                       if (BaseResponse.SUCCESS.equals(result.getCode())) {
                                           mView.showNormal();
                                           callback.onSuccess(result.getData());
                                       } else {
                                           mView.showError(result.getMessage(), result.getCode());
                                           mView.showError();
                                       }
                                   }
                               }
                );
    }

}
