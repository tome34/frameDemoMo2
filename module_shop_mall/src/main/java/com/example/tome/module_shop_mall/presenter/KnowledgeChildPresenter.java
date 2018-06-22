package com.example.tome.module_shop_mall.presenter;

import com.example.tome.component_base.base.BaseObserver;
import com.example.tome.component_base.base.BasePresenter;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_base.util.L;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelService;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import com.example.tome.module_shop_mall.bean.KnowledgeChildBean;
import com.example.tome.module_shop_mall.contract.KnowledgeChildContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import io.reactivex.Observable;

/**
 * @Created by TOME .
 * @时间 2018/6/19 20:27
 * @描述 ${TODO}
 */

public class KnowledgeChildPresenter extends BasePresenter<KnowledgeChildContract.View> implements KnowledgeChildContract.Presenter {
    @Override
    public void getKnowledgeChild(int page, int cid ,SmartRefreshLayout mRefreshLayout) {

        addSubscribe(ModelService.getRemoteListData(mView, mRefreshLayout, new ModelService.MethodSelect<KnowledgeChildBean>() {
            @Override
            public Observable<BaseObj<KnowledgeChildBean>> selectM(ApiService service) {
                return service.getKnowledgeHierarchyDetailData(page, cid);
            }
        }, new INetCallback<KnowledgeChildBean>() {
            @Override
            public void onSuccess(KnowledgeChildBean result) {
                L.d("请求成功:"+result.getTotal());
                mView.showKnowledgeChild(result);
            }
        }));

    }
}
