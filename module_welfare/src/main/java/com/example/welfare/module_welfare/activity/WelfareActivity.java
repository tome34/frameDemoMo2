package com.example.welfare.module_welfare.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.projectCore.base.mvc.BaseVcTabListActivity;
import com.example.tome.projectCore.bean.TabListBean;
import com.fec.core.router.arouter.RouterURLS;
import com.example.welfare.module_welfare.R;
import com.example.welfare.module_welfare.fragment.WelfareTabFragment;
import com.example.welfare.module_welfare.fragment.WelfareV1Fragment;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/5/17 18:20
 * @描述 ${福利页面}
 */
@Route(path = RouterURLS.WELFARE_HOME)
public class WelfareActivity extends BaseVcTabListActivity {

    private WelfareTabFragment mWelfareTabFragment;


    @Override
    protected void initTitle() {
        super.initTitle();

    }

    @Override
    protected void initView() {
        super.initView();
       /* FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (mWelfareTabFragment == null){
            mWelfareTabFragment = new WelfareTabFragment();
            fragmentTransaction.add(R.id.fl_content, mWelfareTabFragment);
        }else {
            fragmentTransaction.show(mWelfareTabFragment);
        }
        fragmentTransaction.commit();*/
    }

  /*  @Override
    protected int getTabLayoutId() {
        return R.layout.welfare_activity_welfare;
    }*/

    @Override
    protected int getLayoutId() {
        super.getLayoutId();
        return R.layout.welfare_activity_welfare;
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

    @Override
    protected String setTitle() {
        return "福利";
    }

}
