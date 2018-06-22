package com.example.tome.module_shop_mall.presenter;

import com.example.tome.component_base.base.BasePresenter;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_data.bean.BaseObj;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelService;
import com.example.tome.module_shop_mall.bean.NavigationBean;
import com.example.tome.module_shop_mall.contract.NavigationContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * @Created by TOME .
 * @时间 2018/6/20 12:01
 * @描述 ${导航的presenter}
 */

public class NavigationPresenter extends BasePresenter<NavigationContract.View> implements NavigationContract.Presenter{
    @Override
    public void getNavigationData() {

        addSubscribe(ModelService.getRemoteData(false, mView, new ModelService.MethodSelect<List<NavigationBean>>() {
            @Override
            public Observable<BaseObj<List<NavigationBean>>> selectM(ApiService service) {
                return service.getNavigationListData();
            }
        }, new INetCallback<List<NavigationBean>>() {
            @Override
            public void onSuccess(List<NavigationBean> result) {
                mView.showNavigation(result);

            }
        }));

    }
}
