package com.example.tome.module_shop_mall.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.net.common_callback.INetCallback;
import com.example.tome.core.util.L;
import com.example.tome.projectCore.bean.BaseObj;
import com.fec.core.router.arouter.RouterURLS;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVcService;
import com.example.tome.module_shop_mall.bean.FeedArticleListData;
import io.reactivex.Observable;

@Route(path = RouterURLS.MVC_TEST)
public class MvcTestActivity extends BaseVcActivity implements View.OnClickListener {

    @BindView(R2.id.tv_test_mvc)
    Button mTvTestMvc;
    @BindView(R2.id.tv_data)
    TextView mTvData;
    @BindView(R2.id.tv_error)
    TextView mTvError;

    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_mvc_test;
    }

    @Override
    protected void initTitle() {
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
    }

    @Override
    protected void initView() {
        mTvTestMvc.setOnClickListener(this);
    }

    protected void loadData() {
        int page = 0;
        addDisposable(ModelVcService.getRemoteData(true, mView, new ModelVcService.MethodSelect<FeedArticleListData>() {
            @Override
            public Observable<BaseObj<FeedArticleListData>> selectM(ApiService service) {
                return service.getFeedArticleList(page);
            }
        }, new INetCallback<FeedArticleListData>() {
            @Override
            public void onSuccess(FeedArticleListData result) {
                L.d("成功返回数据" + result.getCurPage());
                mTvData.setText("成功获取"+result.getDatas().size()+"条数据");
            }

        }));
    }


    @Override
    public void showError(String msg, String code) {
        super.showError(msg, code);
        mTvError.setText("哈哈哈");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_test_mvc){
            loadData();
        }
    }
}
