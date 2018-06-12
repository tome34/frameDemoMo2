package com.example.welfare.module_welfare.presenter;

import com.example.tome.component_base.base.BasePresenter;
import com.example.welfare.module_welfare.api.ApiService;
import com.example.welfare.module_welfare.api.INetCallback;
import com.example.welfare.module_welfare.api.ModelService;
import com.example.welfare.module_welfare.contract.SaveImageContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @Created by TOME .
 * @时间 2018/6/12 16:37
 * @描述 ${下载图片}
 */

public class SaveImagePresenter extends BasePresenter<SaveImageContract.View> implements SaveImageContract.Presenter {

    @Override
    public void downloadImage() {
        addSubscribe(ModelService.getRemoteData(true, mView, new ModelService.MethodSelect<ResponseBody>() {
            @Override
            public Observable<ResponseBody> selectM(ApiService service) {
                return service.downPic("1");
            }
        }, new INetCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {

            }
        }));
    }
}
