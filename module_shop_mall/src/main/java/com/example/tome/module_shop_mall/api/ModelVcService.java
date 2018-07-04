package com.example.tome.module_shop_mall.api;

import com.example.tome.component_base.base.mvc.BaseVcObserver;
import com.example.tome.component_base.base.mvc.inter.BaseView;
import com.example.tome.component_base.base.mvp.inter.IView;
import com.example.tome.component_base.net.HttpHelper;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.RxUtils;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.component_data.bean.BaseResponse;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import io.reactivex.Observable;

/**
 * @Created by TOME .
 * @时间 2018/5/16 9:52
 * @描述 ${TODO}
 */

public class ModelVcService {

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

/*    public static BaseVcObserver<BaseObj<FeedArticleListData>> getRemoteData(int pageNum, MainContract.View mView, INetCallback callback) {
        //设置不同的BaseUrl
        return HttpHelper.getDefault(1)
                .create(ApiService.class)
                .getFeedArticleList(pageNum)
                .compose(RxUtils.<BaseObj<FeedArticleListData>>rxSchedulerHelper())
                .subscribeWith(new BaseVcObserver<BaseObj<FeedArticleListData>>(mView) {
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
    public static <T> BaseVcObserver<BaseObj<T>> getRemoteData(boolean isShowHUD, IView mView, MethodSelect<T> select, INetCallback<T> callback) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(1)
                .create(ApiService.class))
                .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
                .subscribeWith(new BaseVcObserver<BaseObj<T>>(mView, isShowHUD) {
                                   @Override
                                   public void onNext(BaseObj<T> result) {
                                       L.d("获取message", ":" + result.getMessage());
                                       if (BaseResponse.SUCCESS.equals(result.getCode())) {
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
    public static <T> BaseVcObserver<BaseObj<T>> getRemoteListData(IView mView, SmartRefreshLayout rlRefresh, MethodSelect<T> select, INetCallback<T> callback) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(1)
                .create(ApiService.class))
                .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
                .subscribeWith(new BaseVcObserver<BaseObj<T>>(mView, rlRefresh,false) {
                                   @Override
                                   public void onNext(BaseObj<T> result) {
                                       L.d("获取message", ":" + result.getMessage());
                                       if (BaseResponse.SUCCESS.equals(result.getCode())) {
                                           callback.onSuccess(result.getData());
                                       } else {
                                           mView.showError(result.getMessage(), result.getCode());
                                       }
                                   }
                               }
                );
    }

}
