package com.example.tome.module_shop_mall.model;

import com.example.tome.core.base.mvp.DisposablePool;
import com.example.tome.projectCore.bean.BaseObj;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVpService;
import com.example.tome.module_shop_mall.bean.BannerData;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.presenter.HomePresenter;
import io.reactivex.Observable;
import java.util.List;

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
    public Observable<BaseObj<FeedArticleListData>> getFeedArticleList(int page) {
     return  ModelVpService.getRemoteDataVp(new ModelVpService.MethodSelect<FeedArticleListData>() {
            @Override
            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
                return service.getFeedArticleList(page);
            }
        });
    }

  //  @Override
//    public void getBannerData1() {
//        addDisposable(ModelVpService.getRemoteDataVp(new ModelVpService.MethodSelect<List<BannerData>>() {
//                    @Override
//                    public Observable<BaseObj<List<BannerData>>> selectM(ApiService service) {
//                        return service.getBannerData();
//                    }
//                }, new INetCallback<List<BannerData>>() {
//                    @Override
//                    public void onSuccess(List<BannerData> result) {
//                       // mPresenter.BannerData();
//                    }
//                }));
                //new ModelVcService.MethodSelect<List<BannerData>>() {
//            @Override
//            public Observable<BaseObj<List<BannerData>>> selectM(ApiService service) {
//                return service.getBannerData();
//            }
//        }, new INetCallback<List<BannerData>>() {
//            @Override
//            public void onSuccess(List<BannerData> result) {
//               // showBannerData(result);
//            }
//        }));

//    }

    @Override
    public Observable<BaseObj<List<BannerData>>> getBannerData() {

        Observable<BaseObj<List<BannerData>>> remoteDataVp = ModelVpService.getRemoteDataVp(new ModelVpService.MethodSelect<List<BannerData>>() {
            @Override
            public Observable<BaseObj<List<BannerData>>> selectM(ApiService service) {
                return service.getBannerData();
            }
        });

     /*   BaseVpObserver<BaseObj<List<BannerData>>> remoteDataV = ModelVpService.getRemoteDataV(new ModelVcService.MethodSelect<List<BannerData>>() {
            @Override
            public Observable<BaseObj<List<BannerData>>> selectM(ApiService service) {
                return service.getBannerData();
            }
        }, new INetCallback<List<BannerData>>() {
            @Override
            public void onSuccess(List<BannerData> result) {
                // showBannerData(result);
            }
        });*/

       // addDisposable(remoteDataV);

        return remoteDataVp;
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
