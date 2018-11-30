package com.example.welfare.module_welfare.fragment;

import com.example.tome.projectCore.base.mvc.BaseVcTabListFragment;
import com.example.tome.projectCore.bean.TabListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/5 16:20
 * @描述 ${TODO}
 */

public class WelfareTabFragment extends BaseVcTabListFragment {

    @Override
    protected String setTitle() {
        return "福利";
    }

    @Override
    protected List<TabListBean> tabTitles() {
        List<TabListBean> list = new ArrayList<>();
        TabListBean bean1 = new TabListBean("妹纸", WelfareV1Fragment.instance(""));
        TabListBean bean2 = new TabListBean("丑妹", WelfareV1Fragment.instance(""));
        list.add(bean1);
        list.add(bean2);
        return list;
    }





}
