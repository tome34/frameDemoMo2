package com.example.tome.module_shop_cart.listener;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import com.example.tome.module_shop_cart.annotation.OnKeyType;

@SuppressWarnings("all")
public class SimpleOnKeyListener implements OnKeyListener {
    private Activity activity;
    private int onKeyType;

    public SimpleOnKeyListener(Activity activity, @OnKeyType int onKeyType) {
        this.onKeyType = onKeyType;
        this.activity = activity;
    }

    public static SimpleOnKeyListener dismissNotKillActivity(Activity activity) {
        return new SimpleOnKeyListener(activity, OnKeyType.DISMISS_NOT_KILL_ACTIVITY);
    }

    public static SimpleOnKeyListener dismissKillActivity(Activity activity) {
        return new SimpleOnKeyListener(activity, OnKeyType.DISMISS_KILL_ACTIVITY);
    }

    public static SimpleOnKeyListener notDismissNotKillActivity(Activity activity) {
        return new SimpleOnKeyListener(activity, OnKeyType.NOT_DISMISS_NOT_KILL_ACTIVITY);
    }

    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (OnKeyType.DISMISS_KILL_ACTIVITY == onKeyType) {
                dialog.dismiss();
                activity.finish();
            } else if (OnKeyType.DISMISS_NOT_KILL_ACTIVITY == onKeyType) {
                dialog.dismiss();
            } else if (OnKeyType.NOT_DISMISS_NOT_KILL_ACTIVITY == onKeyType) {

            }
        }
        return true;
    }

}