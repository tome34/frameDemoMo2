package com.example.tome.component_base.util;

import android.content.Context;

import com.example.tome.component_base.helper.HUDFactory;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * @Created by TOME .
 * @时间 2018/5/18 17:24
 * @描述 ${加载网络数据,进度条管理}
 */

public class ProgressManage {
    private static ProgressManage mProgressManage;
    public KProgressHUD kProgressHUD;

    private ProgressManage(){

    }

    public static ProgressManage getInstance(){

        if (mProgressManage == null){
            synchronized (ProgressManage.class){
                if (mProgressManage == null){
                    mProgressManage = new ProgressManage();
                }
            }
        }
        return mProgressManage;
    }

    public void showHUD(Context context) {
        if (kProgressHUD == null){
            kProgressHUD = HUDFactory.getInstance().creatHUD(context);
        }
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                //.setLabel(getString(R.string.loading))
                .setLabel(null)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.3f).show();
    }

    public void dismissHUD() {
        if (null != kProgressHUD && kProgressHUD.isShowing()) {
            kProgressHUD.dismiss();
        }
    }

}
