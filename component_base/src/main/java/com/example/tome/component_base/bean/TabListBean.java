package com.example.tome.component_base.bean;

import com.example.tome.component_base.base.mvp.BaseMVPFragment;

/**
 * @Created by TOME .
 * @时间 2018/6/5 15:27
 * @描述 ${}
 */
public class TabListBean {
    private String title;
    private BaseMVPFragment fragment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BaseMVPFragment getFragment() {
        return fragment;
    }

    public void setFragment(BaseMVPFragment fragment) {
        this.fragment = fragment;
    }

    public TabListBean(String title, BaseMVPFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
}
