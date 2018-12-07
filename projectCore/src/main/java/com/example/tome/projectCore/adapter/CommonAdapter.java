package com.example.tome.projectCore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * @author quanxp
 * @date 2017/6/20 11:17
 * Adapter
 */


public abstract class CommonAdapter<T> extends BaseAdapter {


    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mData = null;
    protected final int mItemLayoutId;

    /**
     * @param mContext     上下文对象
     * @param mData        数据源
     * @param itemLayoutId listview对应的Item
     */
    public CommonAdapter(Context mContext, List<T> mData, int itemLayoutId) {
        super();
        mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mData = mData;
        this.mItemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mData != null && mData.size() > 0 ? mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommViewHolder viewHolder = CommViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);

        convert(viewHolder, getItem(position));

        return viewHolder.getConvertView();
    }

    public abstract void convert(CommViewHolder holder, T item);


}
