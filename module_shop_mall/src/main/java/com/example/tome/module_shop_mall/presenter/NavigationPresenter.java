package com.example.tome.module_shop_mall.presenter;

import com.example.tome.core.base.mvp.BasePresenter;
import com.example.tome.module_shop_mall.contract.NavigationContract;
import com.example.tome.module_shop_mall.model.NavigationModel;

/**
 * @Created by TOME .
 * @时间 2018/6/20 12:01
 * @描述 ${导航的presenter}
 */

public class NavigationPresenter extends BasePresenter<NavigationContract.View, NavigationContract.Model> implements NavigationContract.Presenter{

    @Override
    protected NavigationContract.Model createModel() {
        return new NavigationModel(this);
    }

    @Override
    public void getNavigation() {

        mModel.getNavigationData();





       /* addDisposable(ModelVcService.getRemoteData(false, mView, new ModelVcService.MethodSelect<List<NavigationBean>>() {
            @Override
            public Observable<BaseObj<List<NavigationBean>>> selectM(ApiService service) {
                return service.getNavigationListData();
            }
        }, new INetCallback<List<NavigationBean>>() {
            @Override
            public void onSuccess(List<NavigationBean> result) {
                mView.showNavigation(result);

            }
        }));*/

    }

}
