package com.example.tome.framedemomo2;

import com.example.tome.core.constants.BaseApplication;
import com.fec.core.router.arouter.RouterConfig;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * @Created by TOME .
 * @时间 2018/5/14 17:40
 * @描述 ${应用的application}
 */

public class MyApplication extends BaseApplication {

    private static MyApplication myApplication;
//初始化
    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this ;
        //arouter路由初始化
        RouterConfig.init(this, BuildConfig.DEBUG);
        ZXingLibrary.initDisplayOpinion(this);
    }
}
