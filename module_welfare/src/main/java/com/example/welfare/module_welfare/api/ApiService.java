package com.example.welfare.module_welfare.api;


import com.example.welfare.module_welfare.bean.PhotoGirlBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Created by TOME .
 * @时间 2018/5/15 18:18
 * @描述 ${TODO}
 */

public interface ApiService {

    @GET("data/福利/{size}/{page}")
    Observable<PhotoGirlBean> getPhotoList(
            @Path("size") int size,
            @Path("page") int page);

    /**
     * 下载图片
     * @param fileUrl
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downPic(@Url String fileUrl );
}
