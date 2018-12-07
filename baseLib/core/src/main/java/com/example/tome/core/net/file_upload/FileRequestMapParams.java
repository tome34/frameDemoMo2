package com.example.tome.core.net.file_upload;

import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.google.gson.Gson;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by XQ Yang on 2017/9/6  16:20.
 * Description : 文件参数工厂
 */

public class FileRequestMapParams implements FileRequestMapBuild {
    private MultipartBody.Builder mBuilder;
    private MediaType dataMediaType = MediaType.parse("multipart/form-data");
    private ProgressResponseCallBack mAllCallBack;
    private Map<String, File> mFileMap;
    private AtomicLong totalLength;
    private AtomicLong currentLength;
    private Set<String> fileKeys;
    private Gson mGson;

    public FileRequestMapParams() {
        mBuilder = new MultipartBody.Builder();
        mFileMap = new HashMap<>();
    }

    @Override
    public FileRequestMapParams put(String key, String value) {
        if (value!=null) {
            mBuilder.addFormDataPart(key, value);
        }
        return this;
    }

    @Override
    public FileRequestMapParams put(Map<String,String> map) {
        for (Map.Entry<String,String> entry : map.entrySet()) {
            if (!TextUtils.isEmpty(entry.getValue())) {
                mBuilder.addFormDataPart(entry.getKey(),entry.getValue());
            }
        }
        return this;
    }

    @Override
    public FileRequestMapParams put(String key, Object value) {
        if (value!=null) {
            return put(key, getGson().toJson(value));
        }
        return this;
    }

    @Override
    public FileRequestMapParams put(String key, int value) {
        mBuilder.addFormDataPart(key, String.valueOf(value));
        return this;
    }

    @Override
    public FileRequestMapParams put(@NonNull String s, @NonNull String[] arrayOf) {
        mBuilder.addFormDataPart(s, getGson().toJson(arrayOf));
        return this;
    }

    @Override
    public FileRequestMapParams put(String key, File file) {
        mFileMap.put(key, file);
        return this;
    }


    @Override
    public FileRequestMapBuild addAllProgressCallBack(ProgressResponseCallBack callBack) {
        mAllCallBack = callBack;
        return this;
    }

    @Override
    public MultipartBody build() {
        for (final Map.Entry<String, File> entry : mFileMap.entrySet()) {
            File file = entry.getValue();
            RequestBody body;
            if (mAllCallBack == null) {
                 body = RequestBody.create(dataMediaType, file);
            } else {
                if (totalLength == null) {
                    totalLength = new AtomicLong();
                    currentLength = new AtomicLong();
                    fileKeys = Collections.synchronizedSet(new HashSet<String>(mFileMap.size()));
                }
                totalLength.getAndAdd(file.length());
                fileKeys.add(entry.getKey());
                body = new UploadProgressRequestBody(RequestBody.create(dataMediaType, file), new ProgressResponseCallBack() {
                    @Override
                    public void onResponseProgress(long bytesWritten, long contentLength, boolean done) {
                        if (done) {
                            fileKeys.remove(entry.getKey());
                        }
                        mAllCallBack.onResponseProgress(currentLength.addAndGet(bytesWritten),totalLength.get(),fileKeys.isEmpty());
                    }
                });
            }
            mBuilder.addFormDataPart(entry.getKey(), file.getName(), body);
        }

       // ReqConstrants instance = ReqConstrants.getInstance();
      //  if (instance == null) {
       //     return mBuilder.build();
      //  }
        mBuilder.addFormDataPart("appId", "");
        mBuilder.addFormDataPart("token", "");
        mBuilder.addFormDataPart("imei", "");
        mBuilder.addFormDataPart("version", "");
        mBuilder.addFormDataPart("timeStamp", String.valueOf(System.currentTimeMillis()));
        //mBuilder.addFormDataPart("sign", createSign(mBuilder, "key"));//文件上传没有加入sign
        return mBuilder.build();
    }

    private Gson getGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }
}
