package com.example.tome.module_shop_cart.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * 水平分隔线
 * Created by Administrator on 2017/12/1.
 */

public class LinerDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int driver = 4;

    public LinerDividerItemDecoration(Context context, float driver) {
        this.driver = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, driver, context.getResources().getDisplayMetrics());
    }
    public LinerDividerItemDecoration(Context context, int driver) {
        this.driver = driver;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = driver;
    }

}
