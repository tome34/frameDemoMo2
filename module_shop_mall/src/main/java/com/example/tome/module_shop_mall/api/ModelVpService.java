package com.example.tome.module_shop_mall.api;

import com.example.tome.component_base.base.mvc.BaseObserver;
import com.example.tome.component_base.base.mvc.inter.BaseView;
import com.example.tome.component_base.net.HttpHelper;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.RxUtils;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.component_data.bean.BaseResponse;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @author by TOME .
 * @data on      2018/6/29 11:33
 * @describe ${mvp}
 */

public class ModelVpService {

    private ModelVpService mMvpService;
    private ModelVpService mService;

    /**
     * 获取api的回调
     * @param <T>
     */
    public interface MethodSelect<T>{

        Observable<BaseObj<T>> selectM(ApiService service);
    }

    public interface ISubscribe<T>{

        Observable<BaseObj<T>> subscribe(ApiService service);
    }

    public ModelVpService(ModelVpService modelService) {
        mMvpService = modelService;
    }

    public ModelVpService(ModelVpService service, int aa){
        mService = service;
    }

//    public static Observable<BaseObj<FeedArticleListData>> getFeedArticleList(int pageNum) {
//        //设置不同的BaseUrl
//        Retrofit retrofit = HttpHelper.getDefault(1);
//        ApiService apiService = retrofit.create(ApiService.class);
//        return apiService.getFeedArticleList(pageNum);
//    }
//    public static <T> BaseObserver<BaseObj<T>> getRemote(MethodSelect<T> select) {
//        //设置不同的BaseUrl
//        Observable<BaseObj<T>> baseObjObservable = select.selectM(HttpHelper.getDefault(1)
//                .create(ApiService.class));
//
//        return select.selectM(HttpHelper.getDefault(1)
//                .create(ApiService.class))
//                .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
//                .subscribeWith(new BaseObserver<BaseObj<T>>(mView, ) {
//                                   @Override
//                                   public void onNext(BaseObj<T> result) {
//                                       L.d("获取message", ":" + result.getMessage());
//                                       if (BaseResponse.SUCCESS.equals(result.getCode())) {
//                                           callback.onSuccess(result.getData());
//                                       } else {
//                                           mView.showError(result.getMessage(), result.getCode());
//                                       }
//                                   }
//                               }
//                );
//
//        BaseObserver<BaseObj<T>> baseObserver = new BaseObserver<BaseObj<T>>() {
//            @Override
//            public void onNext(BaseObj<T> result) {
//
//            }
//        };
//    }

    public static <T> BaseObserver<BaseObj<T>> getRemoteDataVp(boolean isShowHUD, BaseView mView, MethodSelect<T> select, INetCallback<T> callback) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(1)
                .create(ApiService.class))
                .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
                .subscribeWith(new BaseObserver<BaseObj<T>>(mView, isShowHUD) {
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
