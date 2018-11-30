package com.example.tome.module_shop_mall.fagment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.projectCore.dialog.BaseDialogFragment;
import com.example.tome.core.net.common_callback.INetCallback;
import com.example.tome.core.util.KeyboardUtils;
import com.example.tome.core.util.L;
import com.example.tome.core.util.RxUtils;
import com.example.tome.core.util.OtherUtils;
import com.example.tome.projectCore.bean.BaseObj;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.adapter.HistorySearchAdapter;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVcService;
import com.example.tome.module_shop_mall.bean.SearchHistoryBean;
import com.example.tome.module_shop_mall.bean.TopSearchBean;
import com.example.tome.module_shop_mall.dao.db.LocalSearchDataHelper;
import com.example.tome.module_shop_mall.widget.CircularRevealAnim;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author tome
 * @date 2018/7/10  15:58
 * @describe ${dialog 搜索页面}
 */
public class SearchDialogFragment extends BaseDialogFragment implements CircularRevealAnim.AnimListener, ViewTreeObserver.OnPreDrawListener, View.OnClickListener {

    @BindView(R2.id.iv_search)
    ImageView mIvSearch;
    @BindView(R2.id.et_search)
    EditText mEtSearch;
    @BindView(R2.id.iv_clear)
    ImageView mIvClear;
    @BindView(R2.id.tv_cancel)
    TextView mTvCancel;
    @BindView(R2.id.top_search_flow_layout)
    TagFlowLayout mTopSearchFlowLayout;
    @BindView(R2.id.search_history_clear_all_tv)
    TextView mSearchHistoryClearAllTv;
    @BindView(R2.id.search_history_null_tint_tv)
    TextView mSearchHistoryNullTintTv;
    @BindView(R2.id.search_history_rv)
    RecyclerView mSearchHistoryRv;
    @BindView(R2.id.search_scroll_view)
    NestedScrollView mSearchScrollView;
    @BindView(R2.id.search_coordinator_group)
    CoordinatorLayout mSearchCoordinatorGroup;

    //热搜数据
    private List<TopSearchBean> mTopSearchDataList;
    private CircularRevealAnim mCircularRevealAnim;
    private HistorySearchAdapter historySearchAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME,R.style.mall_DialogStyle);
    }

    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_search;
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    @Override
    protected void initView() {
        mIvClear.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        mSearchHistoryClearAllTv.setOnClickListener(this);
        //初始化圆形动画
        initCircleAnimation();

        mTopSearchDataList = new ArrayList<>();
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(mEtSearch.getText().toString().trim())) {
                    mIvClear.setVisibility(View.GONE);
                } else {
                    mIvClear.setVisibility(View.VISIBLE);
                }
            }
        });

        mEtSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    //如果点击了软键盘的搜索
                    if (keyCode == KeyEvent.KEYCODE_SEARCH||keyCode==KeyEvent.KEYCODE_ENTER) {
                        String keyword = mEtSearch.getText().toString().trim();
                        //添加本地搜索历史
                        addSaveHistorySearch(keyword);
                        //跳转到搜索页面
                      //  Intent intent = new Intent(mContext, SearchListActivity.class);
                       // intent.putExtra(IntentKV.K_SEARCH_KEYWORD, keyword);
                       // mContext.startActivity(intent);
                        L.d("SearchDialogFragment:"+"点击了搜索按钮"+keyword);
                        setHistoryTvStatus(false);
                        // 隐藏软键盘
                        backEvent();
                    }
                }
                return false;
            }
        });

        //显示本地历史记录
        showHistoryData(LocalSearchDataHelper.getInstance().loadAllHistoryData());
        //获取热搜数据
        getTopSearchData();
    }

    //添加本地搜索历史
    private void addSaveHistorySearch(String keyword) {
        addDisposable(Observable.create((ObservableOnSubscribe<List<SearchHistoryBean>>) e -> {
            List<SearchHistoryBean> historyDataList = LocalSearchDataHelper.getInstance().addHistoryData(keyword);
            e.onNext(historyDataList);
        }).compose(RxUtils.rxSchedulerHelper()).subscribe(historyDataList ->
            L.d("本地数据添加成功!")));

    }

    private void getTopSearchData() {
        addDisposable(ModelVcService.getRemoteData(false,mView,new ModelVcService.MethodSelect<List<TopSearchBean>>() {
            @Override
            public Observable<BaseObj<List<TopSearchBean>>> selectM(ApiService service) {
                return service.getTopSearchData();
            }
        },new INetCallback<List<TopSearchBean>>() {
            @Override
            public void onSuccess(List<TopSearchBean> result) {
                L.d("成功返回数据" + result.size());
                showTopSearchData(result);
            }
        }));

    }

    private void showTopSearchData(List<TopSearchBean> topSearchDataResponse) {
        if (topSearchDataResponse == null) {
           // showTopSearchDataFail();
            return;
        }
        mTopSearchDataList = topSearchDataResponse;
        mTopSearchFlowLayout.setAdapter(new TagAdapter<TopSearchBean>(mTopSearchDataList) {
            @Override
            public View getView(FlowLayout parent, int position, TopSearchBean topSearchData) {
                assert getActivity() != null;
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.mall_flow_layout_tv,
                    parent, false);
                assert topSearchData != null;
                String name = topSearchData.getName();
                tv.setText(name);
                //设置tab的背景色
                setItemBackground(tv);
                //设置热搜的点击事件
                mTopSearchFlowLayout.setOnTagClickListener((view, position1, parent1) -> {
                  //  mPresenter.addHistoryData(mTopSearchDataList.get(position1).getName().trim());
                    setHistoryTvStatus(false);
                    mEtSearch.setText(mTopSearchDataList.get(position1).getName().trim());
                    mEtSearch.setSelection(mEtSearch.getText().length());
                    return true;
                });
                return tv;
            }
        });
    }

    private void setItemBackground(TextView tv) {
        tv.setBackgroundColor(OtherUtils.randomTagColor());
        tv.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
    }

    public void showHistoryData(List<SearchHistoryBean> historyDataList) {
        if (historyDataList == null || historyDataList.size() <= 0) {
            setHistoryTvStatus(true);
            return;
        }
        setHistoryTvStatus(false);
        //对list集合反转排序
        Collections.reverse(historyDataList);
        historySearchAdapter = new HistorySearchAdapter(R.layout.mall_item_search_history, historyDataList);
        historySearchAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            SearchHistoryBean historyData = (SearchHistoryBean) adapter.getData().get(position);
            L.d("搜索本地条目点击事件");
           // mPresenter.addHistoryData(historyData.getData());
            mEtSearch.setText(historyData.getKeyword());
            mEtSearch.setSelection(mEtSearch.getText().length());
            setHistoryTvStatus(false);
        });
        mSearchHistoryRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSearchHistoryRv.setAdapter(historySearchAdapter);
    }

    private void initCircleAnimation() {
        mCircularRevealAnim = new CircularRevealAnim();
        mCircularRevealAnim.setAnimListener(this);
        mEtSearch.getViewTreeObserver().addOnPreDrawListener(this);
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        //DialogSearch的宽
        int width = (int)(metrics.widthPixels * 0.98);
        assert window != null;
        window.setLayout(width,WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
        //取消过渡动画 , 使DialogSearch的出现更加平滑
        window.setWindowAnimations(R.style.mall_DialogEmptyAnimation);
    }


    @Override
    public void onHideAnimationEnd() {
        mEtSearch.setText("");
        dismiss();
    }

    @Override
    public void onShowAnimationEnd() {
        KeyboardUtils.openKeyboard(getActivity(), mEtSearch);
    }

    @Override
    public boolean onPreDraw() {
        mEtSearch.getViewTreeObserver().removeOnPreDrawListener(this);
        mCircularRevealAnim.show(mEtSearch, mRootView);
        return true;
    }

    public void backEvent() {
        //隐藏dialog
        KeyboardUtils.closeKeyboard(getActivity(), mEtSearch);
        mCircularRevealAnim.hide(mEtSearch, mRootView);
    }

    private void setHistoryTvStatus(boolean isClearAll) {
        Drawable drawable;
        mSearchHistoryClearAllTv.setEnabled(!isClearAll);
        if (isClearAll) {
            mSearchHistoryNullTintTv.setVisibility(View.VISIBLE);
            mSearchHistoryClearAllTv.setTextColor(ContextCompat.getColor(getActivity(), R.color.mall_search_grey_gone));
            drawable = ContextCompat.getDrawable(getActivity(), R.drawable.mall_ic_clear_all_gone);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mSearchHistoryClearAllTv.setCompoundDrawables(drawable, null, null, null);
        } else {
            mSearchHistoryNullTintTv.setVisibility(View.GONE);
            mSearchHistoryClearAllTv.setTextColor(ContextCompat.getColor(getActivity(), R.color.mall_search_grey));
            drawable = ContextCompat.getDrawable(getActivity(), R.drawable.mall_ic_clear_all);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mSearchHistoryClearAllTv.setCompoundDrawables(drawable, null, null, null);
        }
        mSearchHistoryClearAllTv.setCompoundDrawablePadding(DensityUtil.dp2px(6));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_clear){
            //清空edit
            mEtSearch.setText("");
        }else if (v.getId() == R.id.tv_cancel){
            //取消
            KeyboardUtils.closeKeyboard(getActivity(), mEtSearch);
            mCircularRevealAnim.hide(mEtSearch, mRootView);
        }else if (v.getId() == R.id.search_history_clear_all_tv){
            //清空本地历史记录
            LocalSearchDataHelper.getInstance().clearHistoryData();
            historySearchAdapter.replaceData(new ArrayList<>());
            setHistoryTvStatus(true);
        }
    }
}
