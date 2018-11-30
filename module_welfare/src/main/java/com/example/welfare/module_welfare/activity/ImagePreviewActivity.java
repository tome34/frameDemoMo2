package com.example.welfare.module_welfare.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tome.projectCore.base.mvc.BaseVcPermissionActivity;
import com.example.tome.core.net.common_callback.INetCallback;
import com.example.tome.core.net.file_download.FileDownLoadCallback;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.fec.core.router.arouter.IntentKV;
import com.fec.core.router.arouter.RouterURLS;
import com.example.welfare.module_welfare.R;
import com.example.welfare.module_welfare.R2;
import com.example.welfare.module_welfare.adapter.ImagePreviewAdapter;
import com.example.welfare.module_welfare.api.ApiService;
import com.example.welfare.module_welfare.api.ModelService;
import com.example.welfare.module_welfare.bean.PreviewBean;
import com.example.welfare.module_welfare.contract.SaveImageContract;
import com.example.welfare.module_welfare.widget.HackyViewPager;
import io.reactivex.Observable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import okhttp3.ResponseBody;

@Route(path = RouterURLS.WELFARE_PREVIEW)
public class ImagePreviewActivity extends BaseVcPermissionActivity implements SaveImageContract.View, View.OnClickListener {

    @BindView(R2.id.view_pager)
    HackyViewPager mViewPager;
    @BindView(R2.id.tv_save)
    TextView mTvSave;
    @BindView(R2.id.tv_pager)
    TextView mTvPager;
    @BindView(R2.id.layout_preview)
    RelativeLayout mLayoutPreview;

    @Autowired
    public ArrayList<PreviewBean> mPreviewBeans ;
    @Autowired
    public int posit ;
    public int cuccentPosit ;


//    @Override
//    protected SaveImagePresenter getPresenter() {
//        return new SaveImagePresenter();
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.welfare_image_preview_activity;
    }

    @Override
    protected void initTitle() {
        mPreviewBeans = getIntent().getParcelableArrayListExtra(IntentKV.K_WELFARE_PHOTO);
        posit = getIntent().getIntExtra(IntentKV.K_WELFARE_POSITION, 0);
        mTvSave.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        ImagePreviewAdapter pagerAdapter = new ImagePreviewAdapter(this, mPreviewBeans);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(posit);

        int size = mPreviewBeans.size();
        mTvPager.setText((posit+1) +"/"+size);
        cuccentPosit = posit + 1 ;
        //滑动监听器OnPageChangeListener
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // position :当前页面，及你点击滑动的页面；arg1:当前页面偏移的百分比；arg2:当前页面偏移的像素位置

            }

            @Override
            public void onPageSelected(int position) {
                // position是当前选中的页面的Position
                L.d("当前页面:"+position);
                cuccentPosit = position + 1 ;
                mTvPager.setText((position+1) +"/"+size);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //state ==1的时表示正在滑动，state==2的时表示滑动完毕了，state==0的时表示什么都没做。
            }
        });

    }

    /**
     * 保存图片到相册
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (! getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, PERMISSION_STORAGE)){
            return;
        }
       // loadData(mPreviewBeans.get((cuccentPosit - 1)).getUrlString());
       // mPresenter.downloadImage(mPreviewBeans.get((cuccentPosit - 1)).getUrlString());
        ToastUtils.show(ImagePreviewActivity.this,"hahha",1);

    }


    protected void loadData(String imageUrl) {
        addDisposable(ModelService.downloadFile(mView, new ModelService.MethodSelect<ResponseBody>() {
            @Override
            public Observable<ResponseBody> selectM(ApiService service) {
                return service.downPic(imageUrl);
            }
        }, new INetCallback<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody result) {
                showSaveSuccess(result);
            }
        }));
    }

    @Override
    public void showSaveSuccess(ResponseBody result) {
        long timeMillis = System.currentTimeMillis();
        String string = String.valueOf(timeMillis);
        String mPictureName = string.substring(string.length() - 6, string.length());
        L.d("当前时间:" + timeMillis + "," + mPictureName);
        //保存图片到本地
        File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "avatar");
        FileDownLoadCallback.saveFile(result, Environment.getExternalStorageDirectory().getAbsolutePath()+"/avatar/", "ToastUtils"+mPictureName+".jpg");

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(this.getContentResolver(),
                    fileDir.getAbsolutePath(), "ToastUtils" + mPictureName + ".jpg", null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/avatar/" + "ToastUtils" + mPictureName + ".jpg";
        // 最后通知图库更新
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
    }

}
