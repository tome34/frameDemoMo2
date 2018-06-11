package com.example.tome.framedemomo2;

import android.support.multidex.MultiDex;

import com.example.tome.component_base.arouter.RouterConfig;
import com.example.tome.component_base.constants.BaseApplication;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * @Created by TOME .
 * @时间 2018/5/14 17:40
 * @描述 ${应用的application}
 */

public class MyApplication extends BaseApplication {

    private static MyApplication myApplication;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this ;
        //RouterConfig.init(this, com.example.tome.component_base.BuildConfig.DEBUG);
       // Stetho.initializeWithDefaults(this);
    }
}
