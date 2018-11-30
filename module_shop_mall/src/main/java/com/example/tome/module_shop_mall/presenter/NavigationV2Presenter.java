package com.example.tome.module_shop_mall.presenter;

import com.example.tome.core.base.BaseObserver;
import com.example.tome.core.base.mvp.BasePresenter;
import com.example.tome.projectCore.bean.BaseObj;
import com.example.tome.module_shop_mall.bean.NavigationBean;
import com.example.tome.module_shop_mall.contract.INavigationV2Contract;
import com.example.tome.module_shop_mall.model.NavigationV2Model;
import java.util.List;

/**
 * Description :
 *
 * @author Tome
 * @date 2018/7/18  15:02
 * - generate by MvpAutoCodePlus plugin.
 */

public class NavigationV2Presenter extends BasePresenter<INavigationV2Contract.View,INavigationV2Contract.Model> implements
    INavigationV2Contract.Presenter {

    @Override
    protected INavigationV2Contract.Model createModel() {
        return new NavigationV2Model();
    }

    @Override
    public void getNavigationData() {
        addDisposable(mModel.getNavigationData()
        .subscribeWith(new BaseObserver<BaseObj<List<NavigationBean>>>(mView){
            @Override
            public void onNext(BaseObj<List<NavigationBean>> listBaseObj) {
                List<NavigationBean> data = listBaseObj.getData();
                mView.showNormal();
                mView.showNavigation(data);
            }
        }));
    }
}


