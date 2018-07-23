package com.example.tome.module_common.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.tome.component_base.util.L;
import com.example.tome.component_base.widget.emptyViews.EmptyView;
import com.example.tome.component_base.widget.roundWidget.RoundButton;
import com.example.tome.module_common.R;

public class EmptyLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private RoundButton mButton;
    private EmptyView mEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_layout);
        mEmptyView = (EmptyView)findViewById(R.id.empty_view);
        //自定义圆角button
        mButton = (RoundButton)findViewById(R.id.bn_button);
        mButton.setChangeAlphaWhenDisable(true);
        mButton.setChangeAlphaWhenPress(true);
        mButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bn_button){
            L.d("点击了");
            mEmptyView.hide();
            //正在加载中
            mEmptyView.showLoading();
            Dismiss();
        }

    }

    private void Dismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEmptyView.hide();
                mEmptyView.showError(R.mipmap.icon_error,"出错了",null,"重新加载",new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        L.d("点击了重新刷新");
                        mEmptyView.hide();
                        //正在加载中
                        mEmptyView.showLoading();
                        Dismiss();
                    }
                });
            }
        },2000);
    }
}
