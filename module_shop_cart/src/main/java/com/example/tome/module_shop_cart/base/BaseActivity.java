package com.example.tome.module_shop_cart.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.tome.core.helper.HUDFactory;
import com.example.tome.core.util.L;
import com.example.tome.core.base.BaseEventbusBean;
import com.example.tome.module_shop_cart.R;
import com.kaopiz.kprogresshud.KProgressHUD;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author tome
 * @date 2018/8/10  10:31
 * @describe ${Activity基类}
 */
public abstract class BaseActivity extends AppCompatActivity implements IUIBase{

    private Unbinder unbinder;
    public KProgressHUD kProgressHUD;
    public static int titleBarHeight;
    protected boolean regEvent;
    protected boolean isDestory = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //todo
        setTranslucentStatus();
        setStatusBarFontDark(true);
        setContentView(setContentLayout());

        if (titleBarHeight == 0) {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                titleBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
        }

        unbinder = ButterKnife.bind(this);

        initTitle();
        init(savedInstanceState);
        initView();
        if (regEvent) {
            EventBus.getDefault().register(this);
        }
    }

    /**
     * 带有savedInstanceState 的初始化
     *
     * @param savedInstanceState
     */
    public void init(Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        L.i("当前运行的类：" + getClass().getName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseEventbusBean event) {
        onEvent(event);
    }

    protected void onEvent(BaseEventbusBean event) {

    }

    @Override
    public void showHUD() {
        if (isDestory){
            return;
        }
        kProgressHUD = HUDFactory.getInstance().creatHUD(this);
        kProgressHUD.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel(getString(R.string.loading))
                    //.setLabel(null)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.3f).show();
    }

    @Override
    public void dismissHUD() {
        if (null != kProgressHUD && kProgressHUD.isShowing()) {
            kProgressHUD.dismiss();
        }
    }

    public void requestBefore(int flag) {
        showHUD();
    }

    public void complete(int flag) {
        dismissHUD();
    }

    public void setTitleMargin(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams llytParams = (LinearLayout.LayoutParams) params;
                llytParams.setMargins(0, titleBarHeight, 0, 0);
                L.d("1状态栏titleBarHeight"+titleBarHeight);
            } else if (params instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams llytParams = (RelativeLayout.LayoutParams) params;
                llytParams.setMargins(0, titleBarHeight, 0, 0);
                L.d("2状态栏titleBarHeight"+titleBarHeight);
            }
            view.setLayoutParams(params);
        }
    }

    /**
     * 5.0以上系统状态栏透明
     */
    protected void setTranslucentStatus() {
        // 5.0以上系统状态栏透明
        L.d("状态栏VERSION"+Build.VERSION.SDK_INT +","+Build.VERSION_CODES.LOLLIPOP +","+Build.VERSION_CODES.KITKAT+","+Build.VERSION_CODES.M);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //如果sdk版本大于4.4则设置状态栏透明化 会导致首页状态栏减少
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        }
    }

    /**
     * 设置Android状态栏的字体颜色，状态栏为亮色的时候字体和图标是黑色，状态栏为暗色的时候字体和图标为白色
     *
     * @param dark 状态栏字体和图标是否为深色
     */
    protected void setStatusBarFontDark(boolean dark) {
        // 小米MIUI
        try {
            Window window = getWindow();
            Class clazz = getWindow().getClass();
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            if (dark) {    //状态栏亮色且黑色字体
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
            } else {       //清除黑色字体
                extraFlagField.invoke(window, 0, darkModeFlag);
            }
        } catch (Exception e) {
            L.i("小米MIUI：" + e.toString());
        }

        // 魅族FlymeUI
        try {
            Window window = getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (dark) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            window.setAttributes(lp);
        } catch (Exception e) {
            L.i("魅族FlymeUI：" + e.toString());
        }
        // android6.0+系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (dark) {
                getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }

        if (regEvent) {
            EventBus.getDefault().unregister(this);
        }
        isDestory = true;
        dismissHUD();
    }
}
