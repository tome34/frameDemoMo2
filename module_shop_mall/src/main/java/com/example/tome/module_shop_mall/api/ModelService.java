package com.example.tome.module_shop_mall.api;

import com.example.tome.component_base.net.HttpHelper;
import com.example.tome.module_shop_mall.bean.FeedArticleListResponse;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * @Created by TOME .
 * @时间 2018/5/16 9:52
 * @描述 ${TODO}
 */

public class ModelService {

    private ModelService mModelService ;

    public ModelService(ModelService modelService) {
        mModelService = modelService;

    }

    public static Observable<FeedArticleListResponse> getFeedArticleList(int pageNum) {
        //设置不同的BaseUrl
        Retrofit retrofit = HttpHelper.getDefault(1);
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService.getFeedArticleList(pageNum);
    }


}
