package com.example.tome.core.net.params;

import android.support.annotation.NonNull;

import com.example.tome.core.util.JsonUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by XQ Yang on 2017/9/6  16:20.
 * Description : 通用参数工厂
 */

public class RequestMapParams implements RequestMapBuild<Map<String,String>> {
    private Map<String,String> map;
    private Gson mGson;
    public RequestMapParams() {
        map = new TreeMap<>();
    }

    @Override
    public RequestMapParams put(String key, String value) {
        if (value!=null) {
            map.put(key, value);
        }
        return this;
    }

    @Override
    public RequestMapBuild<Map<String, String>> put(String key, Object value) {
        if (value!=null) {
            map.put(key, getGson().toJson(value));
        }
        return this;
    }

    @Override
    public RequestMapBuild<Map<String, String>> put(String key, int value) {
        map.put(key, String.valueOf(value));
        return this;
    }

    @Override
    public RequestMapBuild<Map<String,String>> put(Map<String,String> map) {
        this.map.putAll(map);
        return this;
    }

    @Override
    public RequestMapBuild<Map<String, String>> put(@NonNull String s, @NonNull String[] arrayOf) {
        map.put(s, getGson().toJson(arrayOf));
        return this;
    }

    private Gson getGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }

    @Override
    public Map<String, String> build() {
        //ReqConstrants instance = ReqConstrants.getInstance();
       // if (instance == null) {
       //     return map;
       // }
        map.put("appId", "");
        map.put("token", "");
        map.put("imei", "");
        map.put("version", "");
        map.put("timeStamp", String.valueOf(System.currentTimeMillis()));
       // map.put("sign", RequestMapBuild.createSign(map, "key"));

        try {
            Logger.json(JsonUtil.mapToJsonObj(map).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // FIXME: 2017/10/20 开发调试期间暂时关闭
        return map;
    }
}
