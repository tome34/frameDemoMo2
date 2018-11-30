package com.example.tome.module_shop_mall.model;

import com.example.tome.core.base.mvp.DisposablePool;
import com.example.tome.module_shop_mall.contract.NavigationContract;
import com.example.tome.module_shop_mall.presenter.NavigationPresenter;

/**
 * @author by TOME .
 * @data on      2018/6/29 14:24
 * @describe ${TODO}
 */

public class NavigationModel  extends DisposablePool implements NavigationContract.Model{

    private NavigationPresenter mPresenter;
    public NavigationModel(NavigationPresenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void getNavigationData() {
       // addDisposable();
    }
}
