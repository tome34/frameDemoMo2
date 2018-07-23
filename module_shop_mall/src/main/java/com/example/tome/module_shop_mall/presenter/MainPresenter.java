package com.example.tome.module_shop_mall.presenter;

import android.app.Activity;
import android.content.Context;
import com.example.tome.component_base.base.BaseObserver;
import com.example.tome.component_base.base.mvp.BasePresenter;
import com.example.tome.component_base.net.file_upload.FileRequestMapParams;
import com.example.tome.component_base.net.params.RequestMapParams;
import com.example.tome.component_base.util.ActivityUtils;
import com.example.tome.component_base.util.L;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.MainContract;
import com.example.tome.module_shop_mall.model.mainMvpModel;
import okhttp3.MultipartBody;

/**
 * @Created by TOME .
 * @时间 2018/5/4 11:03
 * @描述 ${登录preshenter}
 */
        /*addDisposable(ModelVcService.getFeedArticleList(page)
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
              ));*

       // int a  = 10;
     /*  addDisposable(ModelVcService.getRemoteData(page, mView, service -> service.getFeedArticleList(a), result -> {
           mResult = result;
       }));*/

      /* addDisposable(ModelVcService.getRemoteData(mView, new ModelVcService.MethodSelect<FeedArticleListData>() {
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

//        addDisposable(ModelVcService.getRemoteData(true, mView, new ModelVcService.MethodSelect<FeedArticleListData>() {
//            @Override
//            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
//                return service.getFeedArticleList(page, params.build());
//            }
//        }, new INetCallback<FeedArticleListData>() {
//            @Override
//            public void onSuccess(FeedArticleListData result) {
//                L.d("成功返回数据"+result.getCurPage());
//                mView.showTestData(result);
//            }
//        }));
//
public class MainPresenter extends BasePresenter<MainContract.View, MainContract.Model> implements MainContract.Presenter {

    private Context mContext;
    public Activity mCurrentActivity;
    private FeedArticleListData mResult;

    @Override
    public void attachView(MainContract.View view) {
        super.attachView(view);
        mCurrentActivity = ActivityUtils.getInstance().currentActivity();
    }

    @Override
    protected MainContract.Model createModel() {
        return new mainMvpModel();
    }

    @Override
    public void initFeedArticleList() {
        FileRequestMapParams fileParam = new FileRequestMapParams(); //文件上传
        fileParam.put("file", "path");
        MultipartBody build = fileParam.build();
        RequestMapParams params = new RequestMapParams();
        params.put("key", "");
        addDisposable(
                mModel.getFeedArticleList(0, params)
                        .subscribeWith(new BaseObserver<BaseObj<FeedArticleListData>>(mView) {
                            @Override
                            public void onNext(BaseObj<FeedArticleListData> feedArticleListDataBaseObj) {
                                L.d("成功返回数据" + feedArticleListDataBaseObj.getData().getCurPage());
                                mView.showTestData(feedArticleListDataBaseObj.getData());
                            }
                        }));

    }
}
