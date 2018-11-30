package com.example.tome.module_common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tome.core.util.JsonUtil;
import com.fec.core.router.arouter.RouterURLS;
import com.example.tome.module_common.R;
import com.example.tome.module_common.adapter.CommonAdapter;
import com.example.tome.module_common.bean.CommonBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
@Route(path = RouterURLS.CUSTOM_CONTROL)
public class CommonMainActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private TextView mTitleBack;
    private TextView mTitleText;
    private List<CommonBean> mJsonList;
    private CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        initData();
        initView();

    }



    private void initData() {
        //读取json文件
        String json = JsonUtil.getJson(this, "customControl.json");
        mJsonList = new Gson().fromJson(json, new TypeToken<ArrayList<CommonBean>>() {
        }.getType());
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mTitleBack = (TextView) findViewById(R.id.title_back);
        mTitleText = (TextView) findViewById(R.id.title_content_text);
        //设置标题
        mTitleText.setText("常用自定义控件汇总");
        //mTitleBack.setVisibility(View.VISIBLE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //分割线
        //mRecyclerView.addItemDecoration(new SuperDividerItemDecoration.Builder(this)
        //        .setDividerWidth(0)
        //        .setDividerColor(getResources().getColor(R.color.white))
        //        .build());
        mAdapter = new CommonAdapter(R.layout.item_common, mJsonList);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if ("0".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, TagFlowActivity.class));
        }else if ("1".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, AlertViewDemoActivity.class));
        }else if ("2".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, HoverItemActivity.class));
        }else if ("3".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, DragBallActivity.class));
        }else if ("4".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, RatingBarActivity.class));
        }else if ("5".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, PopupWindowActivity.class));
        }else if ("6".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, PickerViewActivity.class));
        }else if ("7".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, MyAccountActivity.class));
        }else if ("8".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, VideoCompressActivity.class));
        }else if ("9".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, PictureSelectorActivity.class));
        }else if ("10".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, KProgressHudActivity.class));
        }else if ("11".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, TipDialogActivity.class));
        }else if ("12".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, EmptyLayoutActivity.class));
        }else if ("13".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, ZxingActivity.class));
        }else if ("14".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, HeaderListActivity.class));
        }else if ("15".equals(mJsonList.get(position).getType())){
            startActivity(new Intent(CommonMainActivity.this, TestViewActivity.class));
        }
    }



    public void jumpTo() {
        if (mRecyclerView != null) {
            mRecyclerView.smoothScrollToPosition(0);
        }
    }
}
