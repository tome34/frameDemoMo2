package com.example.tome.module_shop_mall.contract;

import com.example.tome.core.base.mvp.inter.IModel;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.projectCore.bean.BaseObj;
import com.example.tome.module_shop_mall.bean.ProjectClassifyBean;
import io.reactivex.Observable;
import java.util.List;

/**
 * Description :
 *
 * @author Tome
 * @date 2018/7/18  18:20
 * - generate by MvpAutoCodePlus plugin.
 */

public interface IProjectContract {
    interface View extends IView {

        void showProjectClassifyData(List<ProjectClassifyBean> projectClassifyDataList);

    }

    interface Presenter extends IPresenter<View> {

        /**
         * Get project classify data
         */
        void getProjectClassifyData();

    }

    interface Model extends IModel {

        /**
         * Get project classify data
         */
        Observable<BaseObj<List<ProjectClassifyBean>>> getProjectClassifyData();

    }
}
