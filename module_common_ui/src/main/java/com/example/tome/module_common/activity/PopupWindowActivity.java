package com.example.tome.module_common.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tome.module_common.R;
import com.example.tome.module_common.widget.popupWindow.CommonPopupWindow;
import com.example.tome.module_common.widget.popupWindow.CustomPopWindow;

public class PopupWindowActivity extends AppCompatActivity {

    private CommonPopupWindow popupWindow;
    public View contentView;
    private LinearLayout mLayoutPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mLayoutPopup = (LinearLayout) findViewById(R.id.layout_popup);
    }

    /**
     * 点击弹框
     * @param view
     */
    public void btClick1(View view){
        contentView = LayoutInflater.from(this).inflate(R.layout.popup_child_menu, null);
        TextView tvLike = (TextView)contentView.findViewById(R.id.tv_like);
        if (popupWindow != null && popupWindow.isShowing()){
            return;
        }
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(contentView)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimRight)
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(view, -popupWindow.getWidth(), -view.getHeight());
    }

    public void btClick2(View view){
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_product_detail_video, null);
        //创建并显示popWindow
        final CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)
                .enableBackgroundDark(true)
                .setBgDarkAlpha(0.7f)
                .setAnimationStyle(R.style.pop_bottom_anim)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()
                .showAtLocation(mLayoutPopup, Gravity.BOTTOM, 0, 0);

    }
}
