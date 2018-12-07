package com.example.tome.core.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tome.core.R;
import com.example.tome.core.constants.BaseApplication;

public class ToastUtils {

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     *
     * @param context 上下文对象
     * @param message 需要显示的字符串信息
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }



    /**
     * 短时间显示Toast
     *
     * @param context 上下文对象
     * @param message 资源ID
     */
    public static void showShort(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文对象
     * @param message 需要显示的字符串信息
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context 上下文对象
     * @param message 资源ID
     */
    public static void showLong(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文对象
     * @param message  需要显示的字符串信息
     * @param duration 需要显示的时间，单位毫秒
     */
    public static void show(Context context, CharSequence message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context  上下文对象
     * @param message  资源ID
     * @param duration 需要显示的时间，单位毫秒
     */
    public static void show(Context context, int message, int duration) {
        if (isShow)
            Toast.makeText(context, message, duration).show();
    }

    /**
     * 中间弹出的Toast
     *
     * @param msg 显示内容
     */
    public static void showCenter(String msg) {

        Toast toast = new Toast(BaseApplication.getAppContext());
        View view = LayoutInflater.from(BaseApplication.getAppContext()).inflate(R.layout.otherui_activity_toast, null);
        TextView tvText = (TextView) view.findViewById(R.id.tv_tips);
        if (tvText != null) {
            tvText.setText(msg);
        }
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
