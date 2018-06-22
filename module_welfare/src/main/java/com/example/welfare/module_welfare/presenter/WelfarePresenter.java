package com.example.welfare.module_welfare.presenter;

import com.example.tome.component_base.base.BasePresenter;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.welfare.module_welfare.api.ApiService;
import com.example.welfare.module_welfare.api.ModelService;
import com.example.welfare.module_welfare.bean.PhotoGirlBean;
import com.example.welfare.module_welfare.contract.WelfareContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import io.reactivex.Observable;

/**
 * @Created by TOME .
 * @时间 2018/6/5 16:32
 * @描述 ${TODO}
 */

public class WelfarePresenter extends BasePresenter<WelfareContract.View> implements WelfareContract.Presenter {
    @Override
    public void getPhotosListData(SmartRefreshLayout rlRefreshLayout, int size, int page) {
        addSubscribe(ModelService.getRemoteListData(mView, rlRefreshLayout, new ModelService.MethodSelect<PhotoGirlBean>() {
            @Override
            public Observable<PhotoGirlBean> selectM(ApiService service) {
                return service.getPhotoList(size, page);
            }
        }, new INetCallback<PhotoGirlBean>() {
            @Override
            public void onSuccess(PhotoGirlBean result) {
                mView.showPhotosListData(result);
            }
        }));
    }
}
