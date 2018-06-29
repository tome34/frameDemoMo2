package com.example.tome.module_shop_mall.model;

import com.example.tome.component_base.base.mvc.BaseObserver;
import com.example.tome.component_base.base.mvp.DisposablePool;
import com.example.tome.component_base.base.mvp.BaseVpObserver;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_base.util.RxUtils;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVcService;
import com.example.tome.module_shop_mall.api.ModelVpService;
import com.example.tome.module_shop_mall.bean.BannerData;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.presenter.HomePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author by TOME .
 * @data on      2018/6/29 10:31
 * @describe ${model}
 */

public class HomeModel extends DisposablePool implements HomeContract.Model{

    private HomePresenter mPresenter;
    public HomeModel(HomePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getFeedArticleList(int page) {

    }

    @Override
    public void getFeedArticleListV2(SmartRefreshLayout rlRefreshLayout, int page) {

    }

    @Override
    public void getBannerData() {
      /*  addDisposable(ModelVcService.getRemoteData(false ,mView, new ModelVcService.MethodSelect<List<BannerData>>() {
            @Override
            public Observable<BaseObj<List<BannerData>>> selectM(ApiService service) {
                return service.getBannerData();
            }
        }, new INetCallback<List<BannerData>>() {
            @Override
            public void onSuccess(List<BannerData> result) {
               // showBannerData(result);
            }
        }));*/

    }

//    /**
//     * 获取文章数据
//     * @param page
//     */
//    @Override
//    public void getFeedArticleList(int page) {
//        addDisposable(ModelVcService.getRemoteDataVp(false, mView, service -> service.getFeedArticleList(page), result -> mView.showArticleList(result)));
//    }
//
//    @Override
//    public void getFeedArticleListV2(SmartRefreshLayout rlRefreshLayout, int page) {
//        BaseObserver<BaseObj<FeedArticleListData>> remoteListData = ModelVcService.getRemoteListData(mView, rlRefreshLayout, new ModelVcService.MethodSelect<FeedArticleListData>() {
//            @Override
//            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
//                return service.getFeedArticleList(page);
//            }
//        }, new INetCallback<FeedArticleListData>() {
//            @Override
//            public void onSuccess(FeedArticleListData result) {
//                mPresenter.ArticleList(result);
//            }
//        });
//        addDisposable(remoteListData);
//
//         addDisposable(ModelVpService.getFeedArticleList(page)
//                .compose(RxUtils.<BaseObj<FeedArticleListData>>rxSchedulerHelper())
//                // .filter(feedArticleListResponse -> mView != null)
//
//                .subscribeWith(new BaseVpObserver<BaseObj<FeedArticleListData>>() {
//                    @Override
//                    protected void onNextSuccess(BaseObj<FeedArticleListData> feedArticleListDataBaseObj) {
//
//                    }
//                }
//              ));
//
//        addDisposable(ModelVcService.getRemoteListData(mView, rlRefreshLayout, new ModelVcService.MethodSelect<FeedArticleListData>() {
//            @Override
//            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
//                return service.getFeedArticleList(page);
//            }
//        }, new INetCallback<FeedArticleListData>() {
//            @Override
//            public void onSuccess(FeedArticleListData result) {
//                mView.showArticleList(result);
//            }
//        }));
//
//        mPresenter.onRefresh();
//
//    }
//
//    @Override
//    public void getBannerData() {
//        addDisposable(ModelVcService.getRemoteDataVp(false ,mView, new ModelVcService.MethodSelect<List<BannerData>>() {
//            @Override
//            public Observable<BaseObj<List<BannerData>>> selectM(ApiService service) {
//                return service.getBannerData();
//            }
//        }, new INetCallback<List<BannerData>>() {
//            @Override
//            public void onSuccess(List<BannerData> result) {
//                mView.showBannerData(result);
//            }
//        }));
//
//    }
}
