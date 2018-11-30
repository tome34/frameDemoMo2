package com.example.tome.module_common.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnDismissListener;
import com.bigkoo.alertview.OnItemClickListener;
import com.example.customview.widget.dialog.AlertDialog;
import com.example.tome.core.util.ToastUtils;
import com.example.tome.module_common.R;

/**
 * 精仿iOSAlertViewController控件Demo
 * 仿ios弹出对话窗体
 *https://github.com/Bigkoo/Android-AlertView
 */
public class AlertViewDemoActivity extends Activity implements OnItemClickListener, OnDismissListener {

    //避免创建重复View，先创建View，然后需要的时候show出来，推荐这个做法
    private AlertView mAlertView;
    private AlertView mAlertViewExt;//窗口拓展例子
    private EditText etName;//拓展View内容
    private InputMethodManager imm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertview_demo);
        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        mAlertView = new AlertView("标题", "内容", "取消", new String[]{"确定"}, null, this, AlertView.Style.Alert, this).setCancelable(true).setOnDismissListener(this);
        //拓展窗口
        mAlertViewExt = new AlertView("提示", "请完善你的个人资料！", "取消", null, new String[]{"完成"}, this, AlertView.Style.Alert, this);
        ViewGroup extView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.alertext_form,null);
        etName = (EditText) extView.findViewById(R.id.etName);
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focus) {
                //输入框出来则往上移动
                boolean isOpen=imm.isActive();
                mAlertViewExt.setMarginBottom(isOpen&&focus ? 120 :0);
                System.out.println(isOpen);
            }
        });
        mAlertViewExt.addExtView(extView);
    }

    public void alertShow1(View view) {
        mAlertView.show();
    }

    public void alertShow2(View view) {
        new AlertView("标题", "内容", null, new String[]{"确定"}, null, this, AlertView.Style.Alert, this).show();
    }

    public void alertShow3(View view) {
        new AlertView(null, null, null, new String[]{"高亮按钮1", "高亮按钮2", "高亮按钮3"},
                new String[]{"其他按钮1", "其他按钮2", "其他按钮3", "其他按钮4", "其他按钮5", "其他按钮6",
                        "其他按钮7", "其他按钮8", "其他按钮9", "其他按钮10", "其他按钮11", "其他按钮12"},
                this, AlertView.Style.Alert, this).show();
    }

    public void alertShow4(View view) {
        new AlertView("标题", null, "取消", new String[]{"高亮按钮1"}, new String[]{"其他按钮1", "其他按钮2", "其他按钮3"}, this, AlertView.Style.ActionSheet, this).show();
    }

    public void alertShow5(View view) {
        new AlertView("标题", "内容", "取消", null, null, this, AlertView.Style.ActionSheet, this).setCancelable(true).show();
    }

    public void alertShow6(View view) {
        new AlertView("上传头像", null, "取消", null,
                new String[]{"拍照", "从相册中选择"},
                this, AlertView.Style.ActionSheet, this).show();
    }

    public void alertShowExt(View view) {
        mAlertViewExt.show();
    }

    /**
     * 自己写的diolog,可以自定义布局
     * @param view
     */
    public void alertCustom(View view) {
        AlertDialog dialog = new AlertDialog(this);
        dialog.builder()
                .setTitle("自定义布局")
                .setMsg("哈哈哈")
                .setCancelable(false)
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort(AlertViewDemoActivity.this, "取消");
                    }
                }).setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(AlertViewDemoActivity.this, "确定");
            }
        }).show();
    }
    private void closeKeyboard() {
        //关闭软键盘
        imm.hideSoftInputFromWindow(etName.getWindowToken(),0);
        //恢复位置
        mAlertViewExt.setMarginBottom(0);
    }
    @Override
    public void onItemClick(Object o, int position) {
        closeKeyboard();
        //判断是否是拓展窗口View，而且点击的是非取消按钮
        if(o == mAlertViewExt && position != AlertView.CANCELPOSITION){
            String name = etName.getText().toString();
            if(name.isEmpty()){
                Toast.makeText(this, "啥都没填呢", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "hello,"+name, Toast.LENGTH_SHORT).show();
            }

            return;
        }
        Toast.makeText(this, "点击了第 " + position + "个", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDismiss(Object o) {
        closeKeyboard();
        Toast.makeText(this, "消失了", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            if(mAlertView!=null && mAlertView.isShowing()){
                mAlertView.dismiss();
                return false;
            }
        }

        return super.onKeyDown(keyCode, event);

    }
}
