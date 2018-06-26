package com.example.tome.module_shop_mall.presenter;


import android.app.Activity;
import android.content.Context;

import com.example.tome.component_base.base.BasePresenter;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_base.net.params.RequestMapParams;
import com.example.tome.component_base.util.ActivityUtils;
import com.example.tome.component_base.util.L;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelService;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.MainContract;

import io.reactivex.Observable;

/**
 * @Created by TOME .
 * @时间 2018/5/4 11:03
 * @描述 ${登录preshenter}
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private Context mContext ;
    public Activity mCurrentActivity;
    private FeedArticleListData mResult;

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        mCurrentActivity = ActivityUtils.getInstance().currentActivity();
    }

    @Override
    public void getFeedArticleList(int page,RequestMapParams params) {
        /*addSubscribe(ModelService.getFeedArticleList(page)
                .compose(RxUtils.<FeedArticleListResponse>rxSchedulerHelper())
                // .filter(feedArticleListResponse -> mView != null)
                .subscribeWith(new BaseObserver<FeedArticleListResponse>(mView) { //with 有返回值
                    @Override
                    public void onNext(FeedArticleListResponse feedArticleListResponse) {
                        L.d("获取数据",":"+feedArticleListResponse.getMessage());
                        if (feedArticleListResponse.getCode() == BaseResponse.SUCCESS) {
                            mView.showArticleList(feedArticleListResponse);
                        } else {
                            mView.showArticleListFail();
                        }

                    }

                }
              ));*/

       // int a  = 10;
     /*  addSubscribe(ModelService.getRemoteData(page, mView, service -> service.getFeedArticleList(a), result -> {
           mResult = result;
       }));*/

      /* addSubscribe(ModelService.getRemoteData(mView, new ModelService.MethodSelect<FeedArticleListData>() {
           @Override
           public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
               return service.getFeedArticleList(page);
           }
       }, new INetCallback<FeedArticleListData>() {
           @Override
           public void onSuccess(FeedArticleListData result) {
                L.d("成功返回数据"+result.getCurPage());
           }
       }));*/

        addSubscribe(ModelService.getRemoteData(true, mView, new ModelService.MethodSelect<FeedArticleListData>() {
            @Override
            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
                return service.getFeedArticleList(page, params.build());
            }
        }, new INetCallback<FeedArticleListData>() {
            @Override
            public void onSuccess(FeedArticleListData result) {
                L.d("成功返回数据"+result.getCurPage());
                mView.showTestData(result);
            }
        }));

    }
}
