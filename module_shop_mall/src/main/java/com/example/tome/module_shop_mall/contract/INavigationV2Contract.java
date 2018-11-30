package com.example.tome.module_shop_mall.contract;

import com.example.tome.core.base.mvp.inter.IModel;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.projectCore.bean.BaseObj;
import com.example.tome.module_shop_mall.bean.NavigationBean;
import io.reactivex.Observable;
import java.util.List;

/**
 * Description :
 *
 * @author Tome
 * @date 2018/7/18  15:02
 * - generate by MvpAutoCodePlus plugin.
 */

public interface INavigationV2Contract {
    interface View extends IView {
        void showNavigation(List<NavigationBean> result);
    }

    interface Presenter extends IPresenter<View> {
        void getNavigationData();
    }

    interface Model extends IModel {
        Observable<BaseObj<List<NavigationBean>>> getNavigationData();
    }
}
