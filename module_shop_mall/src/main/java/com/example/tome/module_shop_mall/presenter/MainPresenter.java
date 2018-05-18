package com.example.tome.module_shop_mall.presenter;


import android.content.Context;

import com.example.tome.component_base.base.BaseObserver;
import com.example.tome.component_base.base.BasePresenter;
import com.example.tome.component_base.baseApp.BaseApplication;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.RxUtils;
import com.example.tome.component_data.bean.BaseResponse;
import com.example.tome.module_shop_mall.api.ModelService;
import com.example.tome.module_shop_mall.bean.FeedArticleListResponse;
import com.example.tome.module_shop_mall.contract.MainContract;
import com.example.tome.module_shop_mall.params.LoginParams;

/**
 * @Created by TOME .
 * @时间 2018/5/4 11:03
 * @描述 ${登录preshenter}
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private Context mContext ;
    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);

    }

    @Override
    public void getLoginData(LoginParams params) {
   /*    addSubscribe(mDataManager.getLoginData(params)
       .compose(RxUtils.<LoginResponse>rxSchedulerHelper())
       .subscribeWith(new BaseObserver<LoginResponse>(mView) {
           @Override
           public void onNext(LoginResponse loginResponse) {
               mView.showLoginData(loginResponse);
           }
       }));*/

    }

    @Override
    public void getFeedArticleList(int page) {
        addSubscribe(ModelService.getFeedArticleList(page)
                .compose(RxUtils.<FeedArticleListResponse>rxSchedulerHelper(mContext))
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
              ));


    }
}
