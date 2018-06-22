package com.example.tome.module_shop_mall.presenter;

import com.example.tome.component_base.base.BasePresenter;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelService;
import com.example.tome.module_shop_mall.bean.BannerData;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.bean.KnowledgeSystemBean;
import com.example.tome.module_shop_mall.contract.HomeContract;
import com.example.tome.module_shop_mall.contract.KnowledgeSystemContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import io.reactivex.Observable;

/**
 * @Created by TOME .
 * @时间 2018/5/31 14:01
 * @描述 ${知识体系presenter}
 */

public class KnowledgeSystemPresenter extends BasePresenter<KnowledgeSystemContract.View> implements KnowledgeSystemContract.Presenter {


    @Override
    public void getKnowledgeSystemData() {

        addSubscribe(ModelService.getRemoteData(false, mView, new ModelService.MethodSelect<List<KnowledgeSystemBean>>() {
            @Override
            public Observable<BaseObj<List<KnowledgeSystemBean>>> selectM(ApiService service) {
                return service.getKnowledgeHierarchyData();
            }
        }, new INetCallback<List<KnowledgeSystemBean>>() {
            @Override
            public void onSuccess(List<KnowledgeSystemBean> result) {
                mView.showKnowledgeSystem(result);
            }
        }));

    }
}
