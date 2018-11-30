package com.example.tome.module_shop_mall.presenter;

import com.example.tome.core.base.mvp.BasePresenter;
import com.example.tome.module_shop_mall.contract.KnowledgeChildContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * @Created by TOME .
 * @时间 2018/6/19 20:27
 * @描述 ${TODO}
 */

public class KnowledgeChildPresenter extends BasePresenter<KnowledgeChildContract.View, KnowledgeChildContract.Model> implements KnowledgeChildContract.Presenter {
    @Override
    public void getKnowledgeChild(int page, int cid ,SmartRefreshLayout mRefreshLayout) {

//        addDisposable(ModelVcService.getRemoteListData(mView, mRefreshLayout, new ModelVcService.MethodSelect<KnowledgeChildBean>() {
//            @Override
//            public Observable<BaseObj<KnowledgeChildBean>> selectM(ApiService service) {
//                return service.getKnowledgeHierarchyDetailData(page, cid);
//            }
//        }, new INetCallback<KnowledgeChildBean>() {
//            @Override
//            public void onSuccess(KnowledgeChildBean result) {
//                L.d("请求成功:"+result.getTotal());
//               mView.showKnowledgeChild(result);
//            }
//        }));

    }

    @Override
    protected KnowledgeChildContract.Model createModel() {
        return null;
    }
}
