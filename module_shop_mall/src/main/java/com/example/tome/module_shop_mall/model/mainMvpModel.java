package com.example.tome.module_shop_mall.model;

import com.example.tome.core.base.mvp.DisposablePool;
import com.example.tome.core.net.params.RequestMapParams;
import com.example.tome.projectCore.bean.BaseObj;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVpService;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.contract.MainContract;

import io.reactivex.Observable;

/**
 * @author by TOME .
 * @data on      2018/7/3 17:43
 * @describe ${TODO}
 */

public class mainMvpModel extends DisposablePool implements MainContract.Model {


    @Override
    public Observable<BaseObj<FeedArticleListData>> getFeedArticleList(int page, RequestMapParams params) {
        return ModelVpService.getRemoteDataVp(new ModelVpService.MethodSelect<FeedArticleListData>() {
            @Override
            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
                return service.getFeedArticleList(page, params.build());
            }
        });
    }
}
