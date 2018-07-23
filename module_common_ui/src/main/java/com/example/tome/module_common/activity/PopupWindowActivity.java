package com.example.tome.module_common.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.customview.widget.popupWindow.CommonPopupWindow;
import com.example.customview.widget.popupWindow.CustomPopWindow;
import com.example.tome.module_common.R;
import com.example.tome.module_common.widget.FullSheetDialogFragment;

public class PopupWindowActivity extends AppCompatActivity {

    private CommonPopupWindow popupWindow;
    public View contentView;
    private LinearLayout mLayoutPopup;
    BottomSheetBehavior behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        mLayoutPopup = (LinearLayout) findViewById(R.id.layout_popup);
        View bottomSheet = findViewById(R.id.bottom_sheet);
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet 状态的改变
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画
            }
        });
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
        //根据view的位置设置
        popupWindow.showAsDropDown(view, -popupWindow.getWidth(), -view.getHeight());
    }

    //建议使用
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

//---------------------------------BottomSheet的使用-------------------------------------------------------
    //https://www.jianshu.com/p/0a7383e0ad0f
    public void btClick3(View v){
        if(behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            //STATE_COLLAPSED: 折叠关闭状态。可通过app:behavior_peekHeight来设置显示的高度,peekHeight默认是0。
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }else {
            //STATE_EXPANDED: 完全展开的状态。
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void btClick4(View v){
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.show();
    }

    public void btClick5(View v){
        new FullSheetDialogFragment().show(getSupportFragmentManager(), "dialog");
    }
}
