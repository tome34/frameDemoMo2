package com.example.tome.module_common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import com.example.tome.core.util.L;
import com.example.tome.module_common.R;
import com.example.tome.module_common.widget.HeadZoomScrollView;

public class HeaderListActivity extends AppCompatActivity implements HeadZoomScrollView.OnScrollListener {

    private ImageView mImageView;
    private HeadZoomScrollView mPullScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_list);
        mImageView = findViewById(R.id.background_img);
        mPullScrollView = findViewById(R.id.pull_scroll_view);
        init();
    }

    private void init() {

        mPullScrollView.setOnScrollListener(this);
    }

    @Override
    public void onScroll(int scrollX,int scrollY,int oldScrollX,int oldScrollY) {
        L.d("滑动:"+"scrollX:"+scrollX +",scrollY:"+scrollY +",oldScrollX:"+oldScrollX +",oldScrollY:"+oldScrollY);
    }
}
