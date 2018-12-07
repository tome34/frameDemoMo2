package com.example.tome.projectCore.bean;

import com.example.tome.core.base.mvc.BaseVcFragment;

/**
 * @Created by TOME .
 * @时间 2018/6/5 15:27
 * @描述 ${}
 */
public class TabListBean {
    private String title;
    private BaseVcFragment fragment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BaseVcFragment getFragment() {
        return fragment;
    }

    public void setFragment(BaseVcFragment fragment) {
        this.fragment = fragment;
    }

    public TabListBean(String title, BaseVcFragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
}
