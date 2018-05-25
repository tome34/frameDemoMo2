package com.example.tome.component_base.baseApp;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;

import com.example.tome.component_base.BuildConfig;
import com.example.tome.component_base.arouter.RouterConfig;
import com.example.tome.component_base.base.ActivityControl;
import com.example.tome.component_base.helper.LocaleHelper;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.util.LogUtil;
import com.facebook.stetho.Stetho;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.util.Locale;

/**
 * @Created by TOME .
 * @时间 2018/5/14 17:20
 * @描述 ${保存全局变量设计的基本类application}
 */

public class BaseApplication extends Application{

    public static boolean IS_DEBUG = BuildConfig.DEBUG ;
    private static BaseApplication mBaseApplication ;
    private ActivityControl mActivityControl;//Activity管理

    public ActivityControl getActivityControl() {
        return mActivityControl;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mBaseApplication = this ;
    }

    public static BaseApplication getAppContext(){
        return mBaseApplication;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Locale _UserLocale = LocaleHelper.getLanguage(this);
        //系统语言改变了应用保持之前设置的语言
        if (_UserLocale != null) {
            LocaleHelper.setLocale(this, _UserLocale);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mActivityControl = new ActivityControl();
        //arouter路由初始化
        RouterConfig.init(this, BuildConfig.DEBUG);
        //AutoLayout适配初始化
        AutoLayoutConifg.getInstance().useDeviceSize();
        //Stetho调试工具初始化
        Stetho.initializeWithDefaults(this);
        //
        MultiDex.install(this);
        // 初始化Logger工具
        Logger.addLogAdapter(new AndroidLogAdapter(){
                                 @Override
                                 public boolean isLoggable(int priority, String tag) {
                                     return BuildConfig.DEBUG;
                                 }
                             });
        L.i("是否为debug模式："+IS_DEBUG);
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        exitApp();
    }
    /**
     * 退出应用
     */
    public void exitApp() {
        mActivityControl.finishiAll();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


    /**
     * 低内存的时候执行
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    // 程序在内存清理的时候执行
    /**
     * 程序在内存清理的时候执行
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }

}
