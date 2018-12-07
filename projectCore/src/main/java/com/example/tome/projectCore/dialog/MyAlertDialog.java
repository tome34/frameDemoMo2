package com.example.tome.projectCore.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.tome.core.R;
import com.example.tome.core.util.ObjectUtils;

public class MyAlertDialog {
    private Context      context;
    private Dialog       dialog;
    private LinearLayout lLayout_bg;
    private TextView     txt_title;
    private TextView     txt_msg;
    private Button       btn_neg;
    private Button       btn_pos;
    private ImageView    img_line;
    private LinearLayout viewLayout;
    private Display      display;
    private boolean showTitle      = false;
    private boolean showMsg        = false;
    private boolean showPosBtn     = false;
    private boolean showNegBtn     = false;
    private boolean showViewLayout = false;

    public MyAlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public MyAlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_alertdialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);
        viewLayout = (LinearLayout) view.findViewById(R.id.view_layout);
        viewLayout.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));

        return this;
    }

    public MyAlertDialog setTitle(String title) {
        showTitle = true;
        if (ObjectUtils.isNotEmpty(title)) {
            txt_title.setText(title);
        } else {
            txt_title.setVisibility(View.GONE);
        }
        return this;
    }

    public MyAlertDialog setMsg(CharSequence msg) {
        showMsg = true;
        if (!ObjectUtils.isNotEmpty(msg.toString())) {
            txt_msg.setText("");
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    public MyAlertDialog setContentView(View view) {
        showViewLayout = true;
        if (null != view) {
            viewLayout.addView(view);
        }

        return this;
    }

    public MyAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public MyAlertDialog setPositiveButton(String text,
                                           final OnClickListenerAlertDialog listener) {
        showPosBtn = true;
        if (!ObjectUtils.isNotEmpty(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onClick(v, dialog);
                }
//				dialog.dismiss();
            }
        });
        return this;
    }

    public Window getWindow() {
        return null;
    }

    public interface OnClickListenerAlertDialog {

        public void onClick(View v, Dialog dialog);
    }

    public MyAlertDialog setNegativeButton(String text,
                                           final OnClickListener listener) {
        showNegBtn = true;
        if (!ObjectUtils.isNotEmpty(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onClick(v);
                }
                dialog.dismiss();
            }
        });
        return this;
    }

    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText("提示");
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }
        if (showViewLayout) {
            viewLayout.setVisibility(View.VISIBLE);
        }
        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText("确定");
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
            btn_pos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_right_selector);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_left_selector);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.alertdialog_single_selector);
        }
    }

    public void show() {
        setLayout();
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
