/*package com.example.tome.component_base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.tome.component_base.R;

import java.nio.channels.Selector;


public class BottomDialog extends Dialog {
    private Selector selector;

    public BottomDialog(Context context) {
        super(context, R.style.otherui_bottom_dialog);
    }

    public BottomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public BottomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void init(Context context,Selector selector) {
        this.selector = selector;
        setContentView(selector.getView());
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = dip2px(context, 256);
        window.setAttributes(params);

        window.setGravity(Gravity.BOTTOM);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public void setOnAddressSelectedListener(SelectedListener listener) {
        this.selector.setSelectedListener(listener);
    }

    public static BottomDialog show(Context context) {
        return show(context, null);
    }

    public static BottomDialog show(Context context, SelectedListener listener) {
        BottomDialog dialog = new BottomDialog(context, R.style.otherui_bottom_dialog);
        dialog.selector.setSelectedListener(listener);
        dialog.show();

        return dialog;
    }
}*/
