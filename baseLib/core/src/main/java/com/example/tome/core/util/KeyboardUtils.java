package com.example.tome.core.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.example.tome.core.constants.BaseApplication;

/**
 * @author by TOME .
 * @data on      2018/6/25 13:56
 * @describe ${键盘相关}
 */

public class KeyboardUtils {

    private static int                        sContentViewInvisibleHeightPre;

    private KeyboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 动态显示软键盘
     *
     * @param activity The activity.
     */
    public static void showSoftInput(final Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null){
            return;
        }
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
            view.setFocusable(true);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
        }
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void openKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 动态显示软键盘
     *
     * @param view The view.
     */
    public static void showSoftInput(final View view) {
        InputMethodManager imm =
                (InputMethodManager) BaseApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 动态隐藏软键盘
     *
     * @param activity The activity.
     */
    public static void hideSoftInput(final Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        View view = activity.getCurrentFocus();
        if (view == null) view = new View(activity);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void closeKeyboard(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * 动态隐藏软键盘
     *
     * @param view The view.
     */
    public static void hideSoftInput(final View view) {
        InputMethodManager imm =
                (InputMethodManager) BaseApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 切换键盘显示与否状态
     */
    public static void toggleSoftInput() {
        InputMethodManager imm =
                (InputMethodManager) BaseApplication.getAppContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * 判断软键盘是否可见
     * <p>The minimum height is 200</p>
     *
     * @param activity The activity.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isSoftInputVisible(final Activity activity) {
        return isSoftInputVisible(activity, 200);
    }

    /**
     * Return whether soft input is visible.
     *
     * @param activity             The activity.
     * @param minHeightOfSoftInput The minimum height of soft input.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isSoftInputVisible(final Activity activity,
                                             final int minHeightOfSoftInput) {
        return getContentViewInvisibleHeight(activity) >= minHeightOfSoftInput;
    }

    private static int getContentViewInvisibleHeight(final Activity activity) {
        final View contentView = activity.findViewById(android.R.id.content);
        final Rect outRect = new Rect();
        contentView.getWindowVisibleDisplayFrame(outRect);
        return contentView.getBottom() - outRect.bottom;
    }
}
