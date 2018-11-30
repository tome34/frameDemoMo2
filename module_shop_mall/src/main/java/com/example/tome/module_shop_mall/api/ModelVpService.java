package com.example.tome.module_shop_mall.api;

import com.example.tome.core.net.HttpHelper;
import com.example.tome.core.util.RxUtils;
import com.example.tome.projectCore.bean.BaseObj;
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

    public static <T> Observable<BaseObj<T>> getRemoteDataVp(MethodSelect<T> select) {
        //设置不同的BaseUrl
        ApiService apiService = HttpHelper.getDefault(1).create(ApiService.class);
        Observable<BaseObj<T>> baseObjObservable = select.selectM(HttpHelper.getDefault(1).create(ApiService.class));
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

    //public static <T> BaseVpObserver<BaseObj<T>> getRemoteDataV( ModelVcService.MethodSelect<T> select, INetCallback<T> callback) {
    //    //设置不同的BaseUrl
    //    return select.selectM(HttpHelper.getDefault(1)
    //            .create(ApiService.class))
    //            .compose(RxUtils.<BaseObj<T>>rxSchedulerHelper())
    //            .subscribeWith(new BaseVpObserver<BaseObj<T>>() {
    //
    //                @Override
    //                public void onNext(BaseObj<T> tBaseObj) {
    //
    //                }
    //            });
    //}
}
