package com.example.tome.core.net.params;

import android.support.annotation.NonNull;

import com.example.tome.core.util.Md5;
import com.orhanobut.logger.Logger;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by XQ Yang on 2017/9/6  16:46.
 * Description :
 */

public interface RequestMapBuild<P>{
    //Map<String, String> build();
    P build();
    RequestMapBuild<P> put(String key, String value);

    RequestMapBuild<P> put(String key, Object value);

    RequestMapBuild<P> put(String key, int value);

    RequestMapBuild<P> put(Map<String, String> map);

    RequestMapBuild<P> put(String s, String[] arrayOf);

     @NonNull
     static String createSign(Map<String, String> params, String key) {
        if (params.size() < 1) {
            return "";
        }
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, String>> es = sortedParams.entrySet();
        for (Map.Entry<String, String> entry : es) {
            String k = entry.getKey();
            String v = entry.getValue();
            if (!"appKey".equals(k) && !"sign".equals(k) && null != v && !"".equals(v) && !"action".equals(k) && !"callback".equals(k) && !"_".equals(k)) {
                sb.append(k).append("=").append(v).append("&");
            }
        }
        sb.append("key=").append(key);
         Logger.i("================Sign：" + sb.toString());
        // 算出摘要
        return Md5.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
    }

}
