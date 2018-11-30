package com.example.tome.module_shop_mall.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tome.projectCore.helper.ImageLoaderHelper;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.bean.ProjectListBean;
import java.util.List;

/**
 * @author tome
 * @date 2018/7/23  17:59
 * @describe ${项目类别adapter}
 */
public class ProjectListAdapter extends BaseQuickAdapter<ProjectListBean.DatasBean,BaseViewHolder> {
    public ProjectListAdapter(int layoutResId,@Nullable List<ProjectListBean.DatasBean> data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper,ProjectListBean.DatasBean item) {
        if (!TextUtils.isEmpty(item.getEnvelopePic())) {

            ImageLoaderHelper.getInstance().load(mContext, item.getEnvelopePic(), (ImageView) helper.getView(R.id.item_project_list_iv));
        }
        if (!TextUtils.isEmpty(item.getTitle())) {
            helper.setText(R.id.item_project_list_title_tv, item.getTitle());
        }
        if (!TextUtils.isEmpty(item.getDesc())) {
            helper.setText(R.id.item_project_list_content_tv, item.getDesc());
        }
        if (!TextUtils.isEmpty(item.getNiceDate())) {
            helper.setText(R.id.item_project_list_time_tv, item.getNiceDate());
        }
        if (!TextUtils.isEmpty(item.getAuthor())) {
            helper.setText(R.id.item_project_list_author_tv, item.getAuthor());
        }
        if (!TextUtils.isEmpty(item.getApkLink())) {
            helper.getView(R.id.item_project_list_install_tv).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.item_project_list_install_tv).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.item_project_list_install_tv);
    }
}
