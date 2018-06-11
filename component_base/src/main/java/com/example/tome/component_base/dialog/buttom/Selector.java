/*package com.example.tome.component_base.dialog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.tome.component_base.R;

import java.util.ArrayList;
import java.util.List;


public class Selector implements AdapterView.OnItemClickListener {

    public static final int INDEX_INVALID = -1;
    private final Context context;
    private SelectedListener listener;
    private View view;
    private View indicator;
    private LinearLayout ll_tabLayout;
    private ProgressBar progressBar;
    private ImageView iv_delete;
    private RelativeLayout rlTitle;
    private IDissmissDialog iDissmissDialog;

    private ListView listView;


    private int tabIndex = 0;


    *//* 每个tab的adapter *//* List<List<ISelectAble>> allDatas = new ArrayList<>();
    private SelectAdapter[] adapters;
    *//*选择的深度*//*
    private int selectDeep;
    private int[] selectedIndex;

    DataProvider dataProvider;
    private boolean isTitleShow;

    public void setDataProvider(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
        getNextData(0);
    }

    public Selector(Context context, int deep) {
        this.context = context;
        this.allDatas = new ArrayList<>(deep);
        selectedIndex = new int[deep];
        this.selectDeep = deep;
        for (int i = 0; i < deep; i++) {
            allDatas.add(new ArrayList<ISelectAble>());
        }
        initAdapters();
        initViews();
    }

    private void initAdapters() {
        adapters = new SelectAdapter[allDatas.size()];
        for (int i = 0; i < selectDeep; i++) {
            adapters[i] = new SelectAdapter(allDatas.get(i));
        }
    }

    private TextView[] tabs;

    private void initViews() {
        view = LayoutInflater.from(context).inflate(R.layout.otherui_address_selector, null);

        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        this.listView = (ListView) view.findViewById(R.id.listView);
        this.indicator = view.findViewById(R.id.indicator);
        this.rlTitle = view.findViewById(R.id.rl_address_title);
        this.ll_tabLayout = (LinearLayout) view.findViewById(R.id.layout_tab);
        this.iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iDissmissDialog.dissMiss();
            }
        });
        tabs = new TextView[allDatas.size()];
        for (int i = 0; i < allDatas.size(); i++) {
            //动态新增TextView
            TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.otherui_simple_text_view, ll_tabLayout, false);
            ll_tabLayout.addView(textView);
            //绑定TextView的点击事件
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //设置tab 下标
                    tabIndex = finalI + 1;
                    //更新adapter
                    listView.setAdapter(adapters[finalI]);
                    //设置选择位置
                    if (selectedIndex[finalI] != INDEX_INVALID) {
                        listView.setSelection(selectedIndex[finalI]);
                    }
                    updateTabsVisibility(tabIndex-1);
                    updateIndicator(tabIndex - 1);


                }
            });
            tabs[i] = textView;
        }



        this.listView.setOnItemClickListener(this);


        updateIndicator(tabIndex);
    }

    public View getView() {
        return view;
    }

    //是否显示标题
    public void whetherShowTitle(boolean isTitleShow){
        this.isTitleShow = isTitleShow;
        if (this.rlTitle!=null){
            if (isTitleShow){
                this.rlTitle.setVisibility(View.VISIBLE);
            }else{
                this.rlTitle.setVisibility(View.GONE);
            }
        }
    }


    *//**
     * 指示器动画
     *//*
    private void updateIndicator(final int tabIndex) {
        view.post(new Runnable() {
            @Override
            public void run() {
                buildIndicatorAnimatorTowards(tabs[tabIndex]).start();
            }
        });
    }


    private AnimatorSet buildIndicatorAnimatorTowards(TextView tab) {
        ObjectAnimator xAnimator = ObjectAnimator.ofFloat(indicator, "X", indicator.getX(), tab.getX());

        final ViewGroup.LayoutParams params = indicator.getLayoutParams();
        ValueAnimator widthAnimator = ValueAnimator.ofInt(params.width, tab.getMeasuredWidth());
        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                params.width = (int) animation.getAnimatedValue();
                indicator.setLayoutParams(params);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new FastOutSlowInInterpolator());
        set.playTogether(xAnimator, widthAnimator);

        return set;
    }


    private void updateTabsVisibility(int index) {
        for (int i = 0; i < tabs.length; i++) {
            TextView tv = tabs[i];
            tv.setVisibility(allDatas.get(i).size() != 0 ? View.VISIBLE : View.GONE);
            tv.setEnabled(index != i);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.selectedIndex[tabIndex - 1] = position;


        ISelectAble selectAble = allDatas.get(tabIndex - 1).get(position);
        tabs[tabIndex-1].setText(selectAble.getName());
        for (int i = tabIndex; i < this.allDatas.size(); i++) {
            tabs[i].setText(context.getString(R.string.otherui_date_time_select));
            allDatas.get(i).clear();
            adapters[i].setSelectedIndex(-1);
            adapters[i].notifyDataSetChanged();
            this.selectedIndex[i] = INDEX_INVALID;
        }
        this.adapters[tabIndex - 1].setSelectedIndex(position);
        this.adapters[tabIndex - 1].notifyDataSetChanged();
        if (tabIndex == selectDeep) {
            callbackInternal();
            return;
        }
        updateTabsVisibility(tabIndex -1);
        updateIndicator(tabIndex);
        getNextData(selectAble.getId());
    }

    *//**
     * 根据当前集合选择的id，向用户获取下一级子集的数据
     *//*
    private void getNextData(int preId) {
        if (dataProvider == null) {
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        dataProvider.provideData(tabIndex, preId, new DataProvider.DataReceiver() {
            @Override
            public void send(List<ISelectAble> data) {
                if (data.size() > 0) {
                    //更新当前tab下标
                    allDatas.get(tabIndex).clear();
                    allDatas.get(tabIndex).addAll(data);
                    adapters[tabIndex].notifyDataSetChanged();
                    listView.setAdapter(adapters[tabIndex]);
                } else {
                    //次级没有内容，直接回调
                    callbackInternal();
                }

                updateTabsVisibility(tabIndex);
                updateProgressVisibility();
                updateIndicator(tabIndex);
                tabIndex = tabIndex + 1 >= selectDeep ? selectDeep : tabIndex + 1;

            }
        });
    }

    private void callbackInternal() {
        if (listener != null) {

            ArrayList<ISelectAble> result = new ArrayList<>(allDatas.size());
            for (int i = 0; i < selectDeep; i++) {
                ISelectAble resultBean = allDatas.get(i) == null
                        || selectedIndex[i] == INDEX_INVALID ? null : allDatas.get(i).get(selectedIndex[i]);
                result.add(resultBean);
            }
            listener.onAddressSelected(result);
        }
    }

    private void updateProgressVisibility() {
        ListAdapter adapter = listView.getAdapter();
        int itemCount = adapter.getCount();
        progressBar.setVisibility(itemCount > 0 ? View.GONE : View.VISIBLE);
    }


    public SelectedListener getOnAddressSelectedListener() {
        return listener;
    }

    public void setSelectedListener(SelectedListener listener) {
        this.listener = listener;
    }

    public void setDissMissDialog(IDissmissDialog iDissmissDialog) {
        this.iDissmissDialog = iDissmissDialog;
    }


}*/
