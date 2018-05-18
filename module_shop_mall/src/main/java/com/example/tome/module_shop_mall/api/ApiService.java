package com.example.tome.module_shop_mall.api;

import com.example.tome.module_shop_mall.bean.FeedArticleListResponse;
import com.example.tome.module_shop_mall.bean.LoginResponse;
import com.example.tome.module_shop_mall.params.LoginParams;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @Created by TOME .
 * @时间 2018/5/15 18:18
 * @描述 ${TODO}
 */

public interface ApiService {

    String BASE_URL = "http://www.wanandroid.com/";

    /**
     * 登录
     *
     */
    @FormUrlEncoded
    @POST("register/login")
    Observable<LoginResponse> getLoginData(@Body LoginParams params);


    /**
     * 获取feed文章列表
     *
     * @param num 页数
     * @return Observable<FeedArticleListResponse>
     */
    @GET("article/list/{num}/json")
    Observable<FeedArticleListResponse> getFeedArticleList(@Path("num") int num);
}
