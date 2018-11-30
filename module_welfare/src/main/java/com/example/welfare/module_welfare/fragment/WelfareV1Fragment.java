package com.example.welfare.module_welfare.fragment;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.tome.projectCore.base.mvc.BaseVcListFragment;
import com.example.tome.core.net.common_callback.INetCallback;
import com.example.tome.core.util.L;
import com.example.welfare.module_welfare.R;
import com.example.welfare.module_welfare.R2;
import com.example.welfare.module_welfare.adapter.WelfareV1Adapter;
import com.example.welfare.module_welfare.api.ApiService;
import com.example.welfare.module_welfare.api.ModelService;
import com.example.welfare.module_welfare.arouter.RouterCenter;
import com.example.welfare.module_welfare.bean.PhotoGirlBean;
import com.example.welfare.module_welfare.bean.PreviewBean;
import com.example.welfare.module_welfare.contract.WelfareContract;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created by TOME .
 * @时间 2018/6/5 16:47
 * @描述 ${TODO}
 */

//public class WelfareV1Fragment extends BaseVpListFragment<WelfarePresenter> implements WelfareContract.View {
public class WelfareV1Fragment extends BaseVcListFragment implements WelfareContract.View {

    @BindView(R2.id.rl_view)
    RecyclerView mRlView;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mSrlLayout;

    public WelfareV1Adapter mV1Adapter;
    public PhotoGirlBean mPhotoGirlBean ;
    private List<PhotoGirlBean.ResultsBean> mResults;
    private ArrayList<PreviewBean> mPreviewBeans ;

//    @Override
//    protected WelfarePresenter getPresenter() {
//        return new WelfarePresenter();
//    }

    @Override
    protected int getLayout() {
        return R.layout.welfare_v1_fragment;
    }

    public static WelfareV1Fragment instance(String type) {
        WelfareV1Fragment welfareV1Fragment = new WelfareV1Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        welfareV1Fragment.setArguments(bundle);
        return welfareV1Fragment;
    }

    @Override
    protected void initTitle() {
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
        String type = getArguments().getString("type");
    }

    @Override
    protected void initView() {
        super.rlRefreshLayout = mSrlLayout;
        super.initView();
        //图片预览集合
        mPreviewBeans  = new ArrayList<>();

        //设置RecyclerView管理器
        mRlView.setLayoutManager(new GridLayoutManager(mContext, 2));
        //初始化适配器
        mV1Adapter = new WelfareV1Adapter(R.layout.welfare_v1_item , mResults);
        //设置添加或删除item时的动画，这里使用默认动画
        mRlView.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        mRlView.setAdapter(mV1Adapter);

        mV1Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                L.d("点击了"+position);
                RouterCenter.toPreview(mPreviewBeans, position);
            }
        });
    }

    @Override
    public void loadListData(SmartRefreshLayout rlRefreshLayout, int page, int pageSize) {
       // mPresenter.getPhotosListData(rlRefreshLayout, pageSize, page);
        loadData(pageSize, page);
    }

    protected void loadData(int size, int page ) {
        addDisposable(ModelService.getRemoteListData(mView, rlRefreshLayout, new ModelService.MethodSelect<PhotoGirlBean>() {
            @Override
            public Observable<PhotoGirlBean> selectM(ApiService service) {
                return service.getPhotoList(size, page);
            }
        }, new INetCallback<PhotoGirlBean>() {
            @Override
            public void onSuccess(PhotoGirlBean result) {
                showPhotosListData(result);
            }
        }));
    }

    @Override
    public void showPhotosListData(PhotoGirlBean photoGirls) {

        mResults = photoGirls.getResults();
        if (isRefresh){
            mV1Adapter.replaceData(mResults);
            mPreviewBeans.clear();
            getPhotoList();
        }else {
            mV1Adapter.addData(mResults);
            getPhotoList();
        }

    }

    private void getPhotoList() {
        for (PhotoGirlBean.ResultsBean photoGirl : mResults){
            PreviewBean bean = new PreviewBean();
            bean.setUrlString(photoGirl.getUrl());
            mPreviewBeans.add(bean);
        }
    }

}
