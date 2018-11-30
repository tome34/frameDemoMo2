package com.example.welfare.module_welfare.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.tome.projectCore.helper.ImageLoaderHelper;
import com.example.welfare.module_welfare.R;
import com.example.welfare.module_welfare.activity.ImagePreviewActivity;
import com.example.welfare.module_welfare.bean.PreviewBean;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * @Created by TOME .
 * @时间 2018/6/6 17:41
 * @描述 ${TODO}
 */

public class ImagePreviewAdapter extends PagerAdapter implements PhotoViewAttacher.OnPhotoTapListener {

    private ArrayList<PreviewBean> mPreviewBeans;
    private Context mContext ;
    public ImagePreviewAdapter(Context context, @NonNull ArrayList<PreviewBean> photoList) {
        super();
        this.mContext = context ;
        this.mPreviewBeans = photoList ;
    }

    /**
     * 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
     * @return
     */
    @Override
    public int getCount() {
        if (mPreviewBeans != null){
            return mPreviewBeans.size();
        }else {
            return 0;
        }

    }

    /**
     * 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }

    /**
     * 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，
     * 我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
     * @param container
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.welfare_item_photoview, container, false);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        PhotoView imageView = (PhotoView) view.findViewById(R.id.photo_view);
        PreviewBean previewBean = this.mPreviewBeans.get(position);
        //设置点击监听
        imageView.setOnPhotoTapListener(this);
        ImageLoaderHelper.getInstance().load(mContext, previewBean.getUrlString(), imageView);
        container.addView(view);
        return view ;
    }


    @Override
    public void onPhotoTap(View view, float x, float y) {
        ((ImagePreviewActivity) mContext).finish();
    }
}
