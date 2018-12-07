package com.example.tome.core.util;

import com.orhanobut.logger.Logger;

/**
 * @Created by TOME .
 * @时间 2018/5/18 14:28
 * @描述 ${ Logger库的封装}
 */

public class LogUtil {
    /**
     * 初始化log工具，在app入口处调用
     *
     */

    public static void d(String message) {
        Logger.d(message);
    }

    public static void i(String message) {
        Logger.i(message);
    }

    public static void w(String message, Throwable e) {
        String info = e != null ? e.toString() : "null";
        Logger.w(message + "：" + info);
    }

    public static void e(String message, Throwable e) {
        Logger.e(e, message);
    }

    public static void json(String json) {
        Logger.json(json);
    }
}
