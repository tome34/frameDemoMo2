package com.example.tome.module_common.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tome.module_common.R;
import com.example.tome.module_common.adapter.TagFlowAdapter;
import com.example.tome.module_common.bean.TagBean;

import java.util.ArrayList;
import java.util.List;

public class TagFlowActivity extends AppCompatActivity {
    private RecyclerView tagRecyclerView;

    private static final int MAX = 9;
    private List<TagBean> tagBeanList = new ArrayList<>();

    private TagFlowAdapter tagAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag);

        initView();
        initData();
        setRecyclerView();
    }

    private void initView() {
        tagRecyclerView = (RecyclerView) findViewById(R.id.tag_rv);
    }

    private void initData() {
        tagBeanList.add(new TagBean("1","准时"));
        tagBeanList.add(new TagBean("2","非常绅士"));
        tagBeanList.add(new TagBean("3","非常有礼貌"));
        tagBeanList.add(new TagBean("4","很会照顾女生"));
        tagBeanList.add(new TagBean("5","我的男神是个大暖男哦"));
        tagBeanList.add(new TagBean("6","谈吐优雅"));
        tagBeanList.add(new TagBean("7","送我到楼下"));
        tagBeanList.add(new TagBean("9","迟到"));
        tagBeanList.add(new TagBean("10","态度恶劣"));
        tagBeanList.add(new TagBean("11","有不礼貌行为"));
        tagBeanList.add(new TagBean("12","有侮辱性语言有暴力倾向"));
        tagBeanList.add(new TagBean("13","人身攻击"));
        tagBeanList.add(new TagBean("14","临时改变行程"));
        tagBeanList.add(new TagBean("15","客户迟到并无理要求延长约会时间"));
    }

    private void setRecyclerView() {
        GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
        //setSpanSizeLookup 用法 spanCount/spanSize = 每行的item
        layoutManage.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (tagBeanList.get(position).getTag_name().length()>MAX){
                    return 2;
                }
                return 1;
            }
        });
        tagRecyclerView.setLayoutManager(layoutManage);
        tagAdapter = new TagFlowAdapter(tagBeanList);
        tagRecyclerView.setAdapter(tagAdapter);

    }
}
