package com.fec.core.router.arouter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @Created by TOME .
 * @时间 2018/4/26 10:15
 * @描述 ${路由配置}
 */

public class RouterConfig {

    public static void init(Application app, boolean isDebug) {
        if (true) {
            ARouter.openLog();  // 打印日志
            ARouter.openDebug();    // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(app);      // 尽可能早，推荐在Application中初始化
    }
}
