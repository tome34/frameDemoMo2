package com.example.tome.core.net;

import com.example.tome.core.util.JsonUtil;
import com.example.tome.core.util.LogUtil;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Created by TOME .
 * @时间 2018/5/18 14:32
 * @描述 ${一个网络请求和响应的所有信息合并成一条日志的逻辑处理}
 */

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private StringBuilder mMessage = new StringBuilder();
    @Override
    public void log(String message) {
        // 请求或者响应开始
        if (message.startsWith("--> POST")) {
            mMessage.setLength(0);
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if ((message.startsWith("{") && message.endsWith("}"))
                || (message.startsWith("[") && message.endsWith("]"))) {
            message = JsonUtil.formatJson(JsonUtil.decodeUnicode(message));
        }
        mMessage.append(message.concat("\n"));
        // 响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            LogUtil.d(mMessage.toString());
        }

    }
}
