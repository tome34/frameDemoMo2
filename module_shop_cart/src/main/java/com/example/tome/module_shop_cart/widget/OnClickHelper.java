//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.tome.module_shop_cart.widget;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;


public class OnClickHelper {
    private static OnClickHelper OnClickHelper;

    private OnClickHelper() {
    }

    public static OnClickHelper getInstance() {
        if(OnClickHelper == null) {
            Class var0 = OnClickHelper.class;
            synchronized(OnClickHelper.class) {
                OnClickHelper = OnClickHelper == null?new OnClickHelper():OnClickHelper;
            }
        }

        return OnClickHelper;
    }

    public void onBindClickListener(@NonNull View contentView, @NonNull OnClickListener onClickListener) {
        this.setOnClickListener(contentView, onClickListener);
    }

    private void setOnClickListener(@NonNull View view, @NonNull OnClickListener onClickListener) {
        if(view == null) {
            //LogUtil.w("控件为空");
        } else {
            view.setOnClickListener(onClickListener);
            if(view instanceof ViewGroup) {
                this.setOnClickListener((ViewGroup)view, onClickListener);
            }

        }
    }

    private void setOnClickListener(@NonNull ViewGroup viewGroup, @NonNull OnClickListener onClickListener) {
        try {
            viewGroup.setOnClickListener(onClickListener);

            for(int e = 0; e < viewGroup.getChildCount(); ++e) {
                View v = viewGroup.getChildAt(e);
                if(v instanceof ViewGroup) {
                    this.setOnClickListener((ViewGroup)v, onClickListener);
                } else if(v != null && v instanceof View) {
                    v.setOnClickListener(onClickListener);
                }
            }
        } catch (Exception var5) {
            //LogUtil.e(var5);
        }

    }
}
