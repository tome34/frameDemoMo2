package com.example.welfare.module_welfare.presenter;

import com.example.tome.component_base.base.BasePresenter;
import com.example.tome.component_base.net.common_callback.INetCallback;
import com.example.tome.component_base.net.file_download.FileDownLoadCallback;
import com.example.tome.component_base.util.L;
import com.example.welfare.module_welfare.api.ApiService;
import com.example.welfare.module_welfare.api.ModelService;
import com.example.welfare.module_welfare.contract.SaveImageContract;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @Created by TOME .
 * @时间 2018/6/12 16:37
 * @描述 ${下载图片}
 */

public class SaveImagePresenter extends BasePresenter<SaveImageContract.View> implements SaveImageContract.Presenter {

    /**
     * 下载单文件，可以是大文件，该方法不支持断点下载
     * @param imageUrl
     */
    @Override
    public void downloadImage(String imageUrl) {
        addSubscribe(ModelService.downloadFile(mView, new ModelService.MethodSelect<ResponseBody>() {
            @Override
            public Observable<ResponseBody> selectM(ApiService service) {
                return service.downPic(imageUrl);
            }
        }, new INetCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                mView.showSaveSuccess(result);
            }
        }));
    }
}
