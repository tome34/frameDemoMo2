package com.example.tome.projectCore.helper;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.tome.core.R;
import java.io.File;

/**
 * 参考设置：http://www.tuicool.com/articles/3Af6Zby
 *DiskCacheStrategy.NONE:什么都不缓存
 *DiskCacheStrategy.SOURCE:仅缓存原图(全分辨率的图片)
 *DiskCacheStrategy.RESULT:仅缓存最终的图片,即修改了尺寸或者转换后的图片
 *DiskCacheStrategy.ALL:缓存所有版本的图片,默认模式
 *
 * url =    url.replaceAll("http:/", "http://");
 */
public class ImageLoaderHelper {  //待封装

    public static int IMG_LOADING = R.mipmap.ic_image_loading;
    public static int IMG_ERROR = R.mipmap.ic_empty_picture;

    private static volatile ImageLoaderHelper instance;

    private ImageLoaderHelper() {

    }

    public static ImageLoaderHelper getInstance() {

        if (instance == null) {
            synchronized (ImageLoaderHelper.class) {
                if (instance == null){
                    instance = new ImageLoaderHelper();
                }
            }
        }

        return instance;
    }

    private int optionsInit(int flag) {
        int sourceId = 0;
        if (flag == 0){
            sourceId = R.mipmap.ic_image_loading ;
        }
        return sourceId ;
    }

    public void load(Context context, String url, ImageView iv) {

        if (iv != null && context != null && url != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(optionsInit(0))  //加载中显示的图片
                    .centerCrop()            //图像则位于视图的中央
                   // .override(1090, 1090*3/4)
                    .error(IMG_ERROR) //加载失败时显示的图片centerCrop().
                    .diskCacheStrategy(DiskCacheStrategy.ALL);  //图片缓存
            Glide.with(context).load(url).apply(options).into(iv);
        }

    }
    public void loadV2(Context context, String url, ImageView iv) {


        if (iv != null && context != null && url != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(IMG_LOADING)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(url).apply(options).into(iv);
        }

    }

    public void load(Context context, Uri url, ImageView iv) {


        if (iv != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(IMG_LOADING)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(url).apply(options).into(iv);
        }

    }

    public void load(Context context, String url, ImageView iv, int radius) {

        if (iv != null) {
            RequestOptions options = new RequestOptions()
                    .placeholder(IMG_LOADING)
                    .transform(new GlideRoundTransform(context))
//                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(context)
                    .load(url)
                    .transition(new DrawableTransitionOptions().crossFade(200))
                    .apply(options).into(iv);
        }

    }

    public void load(Context context, File fileName, ImageView iv) {

        if (iv != null) {
            RequestOptions options = new RequestOptions()
                .placeholder(IMG_LOADING)
                .centerCrop()//centerCrop是利用图片图填充ImageView设置的大小
                .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(fileName).apply(options).into(iv);
        }

    }

    /**
     * 加载资源图片
     * @param context
     * @param resourceId
     * @param iv
     */
    public void load(Context context, Integer resourceId, ImageView iv) {

        if (iv != null) {
            RequestOptions options = new RequestOptions()
                .placeholder(IMG_LOADING)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(resourceId).apply(options).into(iv);
        }

    }

}
