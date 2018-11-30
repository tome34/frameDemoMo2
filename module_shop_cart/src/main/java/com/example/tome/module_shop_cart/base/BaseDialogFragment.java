package com.example.tome.module_shop_cart.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.tome.core.util.L;
import com.example.tome.module_shop_cart.R;

/**
 * 类描述    :DialgoFragment基类
 * 包名      : com.fecmobile.uicomponent.baseui
 * 类名称    : BaseDialogFragment
 * 创建人    : ghy
 * 创建时间  : 2017/9/5 14:00
 * 修改人    :
 * 修改时间  :
 * 修改备注  :
 */

public abstract class BaseDialogFragment extends DialogFragment implements IUIBase {
    protected Dialog dialog;
    protected View view;

    protected Unbinder unbinder;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(
                setContentLayout(), null);
//        dialog = new Dialog(getContext(), R.style.otherui_BottomDialog);
        dialog = new Dialog(getContext(), R.style.cart_DeleteStyle);
        dialog.setContentView(view);
        unbinder = ButterKnife.bind(this, view);
        View v = dialog.getWindow().getDecorView().getRootView();
        FrameLayout frameLayout = (FrameLayout) v.findViewById(android.R.id.content);
        ViewGroup contentView = (ViewGroup) frameLayout.getChildAt(0);
        contentView.setLayoutParams(setLayoutParams());
//        view.findViewById(R.id.lLayout_bg).setLayoutParams(setLayoutParams());
        initView();

        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i("--------------------------------------------------------------------当前运行的类：" + getClass().getName());//The Current Runing Class
    }

    @Override
    public void showHUD() {

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
    @Override
    public void dismissHUD() {

    }

    /**
     * 描述      :设置布局参数
     * 方法名    :setLayoutParams
     * param    :void
     * 返回类型  :ViewGroup.LayoutParams 布局参数
     * 创建人    : ghy
     * 创建时间  : 2017/2/24 17:39
     * 修改人    :
     * 修改时间
     * 修改备注
     * throws
     */
//    protected abstract ViewGroup.LayoutParams setLayoutParams();
    protected ViewGroup.LayoutParams setLayoutParams() {
        return new FrameLayout.LayoutParams((int) (getResolutionX(getContext()) * 0.833), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    /**
     * 获取屏幕横向(宽度)分辨率
     *
     * @param context
     * @return
     */
    public static int getResolutionX(Context context) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int width = mDisplayMetrics.widthPixels;
        return width;
    }
}
