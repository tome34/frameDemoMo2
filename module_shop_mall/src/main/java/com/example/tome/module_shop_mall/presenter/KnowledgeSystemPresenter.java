package com.example.tome.module_shop_mall.presenter;

import com.example.tome.core.base.mvp.BasePresenter;
import com.example.tome.module_shop_mall.contract.KnowledgeSystemContract;

/**
 * @Created by TOME .
 * @时间 2018/5/31 14:01
 * @描述 ${知识体系presenter}
 */

public class KnowledgeSystemPresenter extends BasePresenter<KnowledgeSystemContract.View, KnowledgeSystemContract.Model> implements KnowledgeSystemContract.Presenter {

    @Override
    protected KnowledgeSystemContract.Model createModel() {
        return null;
    }

    @Override
    public void getKnowledgeSystemData() {

//        addDisposable(ModelVcService.getRemoteData(false, mView, new ModelVcService.MethodSelect<List<KnowledgeSystemBean>>() {
//            @Override
//            public Observable<BaseObj<List<KnowledgeSystemBean>>> selectM(ApiService service) {
//                return service.getKnowledgeHierarchyData();
//            }
//        }, new INetCallback<List<KnowledgeSystemBean>>() {
//            @Override
//            public void onSuccess(List<KnowledgeSystemBean> result) {
//                mView.showKnowledgeSystem(result);
//            }
//        }));

    }



}
