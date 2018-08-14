package com.example.tome.module_shop_mall.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.gyf.barlibrary.ImmersionBar;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mall_activity_splash);
        ImmersionBar.with(this).transparentBar().init();
        initView();
    }

    private void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RouterCenter.toMain();
                finish();
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
