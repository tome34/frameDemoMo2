package com.example.customview.widget.dialog.tipDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.customview.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 提供一个浮层展示在屏幕中间, 一般使用 {@link TipDialogView.Builder} 或 {@link TipDialogView.CustomBuilder} 生成。
 * <ul>
 * <li>{@link TipDialogView.Builder} 提供了一个图标和一行文字的样式, 其中图标有几种类型可选, 见 {@link TipDialogView.Builder.IconType}</li>
 * <li>{@link TipDialogView.CustomBuilder} 支持传入自定义的 layoutResId, 达到自定义 TipDialog 的效果。</li>
 * </ul>
 *
 * @author cginechen
 * @date 2016-10-14
 */

public class TipDialogView extends Dialog {

    public TipDialogView(Context context) {
        this(context, R.style.QMUI_TipDialog);
    }

    public TipDialogView(Context context, int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDialogWidth();
    }

    private void initDialogWidth() {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams wmLp = window.getAttributes();
            wmLp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            window.setAttributes(wmLp);
        }
    }

    /**
     * 生成默认的 {@link TipDialogView}
     * <p>
     * 提供了一个图标和一行文字的样式, 其中图标有几种类型可选。见 {@link IconType}
     * </p>
     *
     * @see CustomBuilder
     */
    public static class Builder {
        /**
         * 不显示任何icon
         */
        public static final int ICON_TYPE_NOTHING = 0;
        /**
         * 显示 Loading 图标
         */
        public static final int ICON_TYPE_LOADING = 1;
        /**
         * 显示成功图标
         */
        public static final int ICON_TYPE_SUCCESS = 2;
        /**
         * 显示失败图标
         */
        public static final int ICON_TYPE_FAIL = 3;
        /**
         * 显示信息图标
         */
        public static final int ICON_TYPE_INFO = 4;

        @IntDef({ICON_TYPE_NOTHING, ICON_TYPE_LOADING, ICON_TYPE_SUCCESS, ICON_TYPE_FAIL, ICON_TYPE_INFO})
        @Retention(RetentionPolicy.SOURCE)
        public @interface IconType {
        }

        private @IconType int mCurrentIconType = ICON_TYPE_NOTHING;

        private Context mContext;

        private CharSequence mTipWord;

        public Builder(Context context) {
            mContext = context;
        }

        /**
         * 设置 icon 显示的内容
         *
         * @see IconType
         */
        public Builder setIconType(@IconType int iconType) {
            mCurrentIconType = iconType;
            return this;
        }

        /**
         * 设置显示的文案
         */
        public Builder setTipWord(CharSequence tipWord) {
            mTipWord = tipWord;
            return this;
        }

        public TipDialogView create(){
            return create(true);
        }

        /**
         * 创建 Dialog, 但没有弹出来, 如果要弹出来, 请调用返回值的 {@link Dialog#show()} 方法
         *
         * @param cancelable 按系统返回键是否可以取消
         * @return 创建的 Dialog
         */
        public TipDialogView create(boolean cancelable) {
            TipDialogView dialog = new TipDialogView(mContext);
            dialog.setCancelable(cancelable);
            dialog.setContentView(R.layout.qmui_tip_dialog_layout);
            ViewGroup contentWrap = (ViewGroup) dialog.findViewById(R.id.contentWrap);

            if (mCurrentIconType == ICON_TYPE_LOADING) {
                //菊花转圈动画,
                //QMUILoadingView loadingView = new QMUILoadingView(mContext);
                //loadingView.setColor(Color.WHITE);
                //loadingView.setSize(QMUIDisplayHelper.dp2px(mContext, 32));
                //LinearLayout.LayoutParams loadingViewLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //loadingView.setLayoutParams(loadingViewLP);
                //contentWrap.addView(loadingView);

            } else if (mCurrentIconType == ICON_TYPE_SUCCESS || mCurrentIconType == ICON_TYPE_FAIL || mCurrentIconType == ICON_TYPE_INFO) {
                ImageView imageView = new ImageView(mContext);
                LinearLayout.LayoutParams imageViewLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(imageViewLP);

                if (mCurrentIconType == ICON_TYPE_SUCCESS) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.qmui_icon_notify_done));
                } else if (mCurrentIconType == ICON_TYPE_FAIL) {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.qmui_icon_notify_error));
                } else {
                    imageView.setImageDrawable(ContextCompat.getDrawable(mContext, R.mipmap.qmui_icon_notify_info));
                }

                contentWrap.addView(imageView);

            }

            if (mTipWord != null && mTipWord.length() > 0) {
                TextView tipView = new TextView(mContext);
                LinearLayout.LayoutParams tipViewLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                if (mCurrentIconType != ICON_TYPE_NOTHING) {
                    tipViewLP.topMargin = dp2px( 12);
                }
                tipView.setLayoutParams(tipViewLP);

                tipView.setEllipsize(TextUtils.TruncateAt.END);
                tipView.setGravity(Gravity.CENTER);
                tipView.setMaxLines(2);
                tipView.setTextColor(ContextCompat.getColor(mContext, R.color.qmui_config_color_white));
                tipView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                tipView.setText(mTipWord);

                contentWrap.addView(tipView);
            }
            return dialog;
        }

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     * @param dpValue 虚拟像素
     * @return 像素
     */
    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 传入自定义的布局并使用这个布局生成 TipDialog
     */
    public static class CustomBuilder {
        private Context mContext;
        private int mContentLayoutId;

        public CustomBuilder(Context context) {
            mContext = context;
        }

        public CustomBuilder setContent(@LayoutRes int layoutId) {
            mContentLayoutId = layoutId;
            return this;
        }

        /**
         * 创建 Dialog, 但没有弹出来, 如果要弹出来, 请调用返回值的 {@link Dialog#show()} 方法
         *
         * @return 创建的 Dialog
         */
        public TipDialogView create() {
            TipDialogView dialog = new TipDialogView(mContext);
            dialog.setContentView(R.layout.qmui_tip_dialog_layout);
            ViewGroup contentWrap = (ViewGroup) dialog.findViewById(R.id.contentWrap);
            LayoutInflater.from(mContext).inflate(mContentLayoutId, contentWrap, true);
            return dialog;
        }
    }
}
