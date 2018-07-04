package com.example.tome.module_shop_mall.api;

import com.example.tome.component_base.base.mvc.BaseVcObserver;
import com.example.tome.component_base.base.mvc.inter.BaseView;
import com.example.tome.component_base.base.mvp.BaseVpObserver;
import com.example.tome.component_base.net.HttpHelper;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.RxUtils;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.component_data.bean.BaseResponse;

import io.reactivex.Observable;

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

    public ModelVpService(ModelVpService modelService) {
        mMvpService = modelService;
    }


//    public static Observable<BaseObj<FeedArticleListData>> getFeedArticleList(int pageNum) {
//        //设置不同的BaseUrl
//        Retrofit retrofit = HttpHelper.getDefault(1);
//        ApiService apiService = retrofit.create(ApiService.class);
//        return apiService.getFeedArticleList(pageNum);
//    }
//    public static <T> BaseVcObserver<BaseObj<T>> getRemote(MethodSelect<T> select) {
//        //设置不同的BaseUrl
//        Observable<BaseObj<T>> baseObjObservable = select.selectM(HttpHelper.getDefault(1)
//                .create(ApiService.class));
//
//        return select.selectM(HttpHelper.getDefault(1)
//                .create(ApiService.class))
//                .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
//                .subscribeWith(new BaseVcObserver<BaseObj<T>>(mView, ) {
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
//        BaseVcObserver<BaseObj<T>> baseObserver = new BaseVcObserver<BaseObj<T>>() {
//            @Override
//            public void onNext(BaseObj<T> result) {
//
//            }
//        };
//    }

    public static <T> Observable<BaseObj<T>> getRemoteDataVp(MethodSelect<T> select) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(1)
                .create(ApiService.class))
                .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
                ;
//                .subscribeWith(new BaseVpObserver<BaseObj<T>>() {
//                                   @Override
//                                   protected void onNextSuccess(BaseObj<T> tBaseObj) {
//
//                                       callback.onSuccess(tBaseObj.getData());
//                                   }
//                               }
//                );
    }

    public static <T> BaseVpObserver<BaseObj<T>> getRemoteDataV( ModelVcService.MethodSelect<T> select, INetCallback<T> callback) {
        //设置不同的BaseUrl
        return select.selectM(HttpHelper.getDefault(1)
                .create(ApiService.class))
                .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
                .subscribeWith(new BaseVpObserver<BaseObj<T>>() {

                    @Override
                    public void onNext(BaseObj<T> tBaseObj) {

                    }
                });
    }
}
