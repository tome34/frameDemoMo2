package com.example.tome.module_shop_mall.presenter;

import com.example.tome.core.base.BaseObserver;
import com.example.tome.core.base.mvp.BasePresenter;
import com.example.tome.projectCore.bean.BaseObj;
import com.example.tome.module_shop_mall.bean.ProjectClassifyBean;
import com.example.tome.module_shop_mall.contract.IProjectContract;
import com.example.tome.module_shop_mall.model.ProjectModel;
import java.util.List;

/**
 * Description :
 *
 * @author Tome
 * @date 2018/7/18  18:20
 * - generate by MvpAutoCodePlus plugin.
 */

public class ProjectPresenter extends BasePresenter<IProjectContract.View,IProjectContract.Model> implements IProjectContract.Presenter {

    @Override
    protected IProjectContract.Model createModel() {
        return new ProjectModel();
    }

    @Override
    public void getProjectClassifyData() {
        addDisposable(mModel.getProjectClassifyData()
                            .subscribeWith(new BaseObserver<BaseObj<List<ProjectClassifyBean>>>(){
                                @Override
                                public void onNext(BaseObj<List<ProjectClassifyBean>> listBaseObj) {
                                    List<ProjectClassifyBean> data = listBaseObj.getData();
                                    mView.showProjectClassifyData(data);
                                }
                            }));

    }

}

