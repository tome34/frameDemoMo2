package com.example.tome.module_shop_mall.presenter;

import com.example.tome.component_base.base.BasePresenter;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelService;
import com.example.tome.module_shop_mall.bean.BannerData;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import io.reactivex.Observable;

/**
 * @Created by TOME .
 * @时间 2018/5/31 14:01
 * @描述 ${TODO}
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    /**
     * 获取文章数据
     * @param page
     */
    @Override
    public void getFeedArticleList(int page) {
        addSubscribe(ModelService.getRemoteData(false, mView, service -> service.getFeedArticleList(page), result -> mView.showArticleList(result)));
    }

    @Override
    public void getFeedArticleListV2(SmartRefreshLayout rlRefreshLayout, int page) {
        addSubscribe(ModelService.getRemoteListData(mView, rlRefreshLayout, new ModelService.MethodSelect<FeedArticleListData>() {
            @Override
            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
                return service.getFeedArticleList(page);
            }
        }, new INetCallback<FeedArticleListData>() {
            @Override
            public void onSuccess(FeedArticleListData result) {
                mView.showArticleList(result);
            }
        }));

    }

    @Override
    public void getBannerData() {
        addSubscribe(ModelService.getRemoteData(false ,mView, new ModelService.MethodSelect<List<BannerData>>() {
            @Override
            public Observable<BaseObj<List<BannerData>>> selectM(ApiService service) {
                return service.getBannerData();
            }
        }, new INetCallback<List<BannerData>>() {
            @Override
            public void onSuccess(List<BannerData> result) {
                mView.showBannerData(result);
            }
        }));

    }
}
