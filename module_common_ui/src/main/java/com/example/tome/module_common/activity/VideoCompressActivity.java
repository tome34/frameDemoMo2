package com.example.tome.module_common.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bumptech.glide.Glide;
import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.util.FileUtils;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ToastUtils;
import com.fec.core.router.arouter.IntentKV;
import com.example.tome.module_common.R;
import com.example.tome.module_common.R2;
import com.example.tome.module_common.utils.getPathByUri;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoCompressActivity extends BaseVcActivity implements View.OnClickListener, OnItemClickListener {

    @BindView(R2.id.videoplayer)
    JCVideoPlayerStandard mVideoplayer;
    @BindView(R2.id.delete_iv)
    ImageView mDeleteIv;
    @BindView(R2.id.layout_videoplayer)
    RelativeLayout mLayoutVideoplayer;
    @BindView(R2.id.tv_size)
    TextView mTvSize;
    @BindView(R2.id.tv_compress_size)
    TextView mTvCompressSize;
    @BindView(R2.id.tv_add_video)
    TextView mTvAddVideo;
    @BindView(R2.id.tv_compress_video)
    TextView mTvCompressVideo;

    private Intent mIntent;
    private Uri mvideoUri;
    private File mTmpFile;
    private static String sThumbPath = null;
    private static String sName = null;
    private boolean mMediasPlayFlag = false;
    private String mMediasUrl = null;
    private Disposable disposable1;
    private String mOutPath = null;

    private Uri mCropUri;
    private File mOriginalFile;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_compress;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        mDeleteIv.setOnClickListener(this);
        mTvAddVideo.setOnClickListener(this);
        mTvCompressVideo.setOnClickListener(this);
        //设置宽高
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels; // 屏幕宽（像素，如：3200px）
        ViewGroup.LayoutParams paramjc = mVideoplayer.getLayoutParams();
        paramjc.width = screenWidth;
        paramjc.height = screenWidth;
        //LogUtils.d("长度:" + screenWidth);

        mVideoplayer.setLayoutParams(paramjc);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.delete_iv){

        }else if (v.getId() == R.id.tv_add_video){
            new AlertView(null, null, "取消", null,
                    new String[]{"录制视频", "从相册选择"},
                    this, AlertView.Style.ActionSheet, this).show();

        }else if (v.getId() == R.id.tv_compress_video){
            //视频压缩
            videoCompres();
        }
    }


    @Override
    public void onItemClick(Object o, int position) {
        if (position == 0){
            //6.0权限问题
            //录制视频
            recordVideo();
        }else if (position == 1){

            //先更新媒体库，发送广播，系统接收到广播就去扫描媒体库
            Uri localUri = Uri.parse("file://" + Environment.getExternalStorageDirectory() + "/avatar/");
            // LogUtils.d("file://"+mediaFile+ File.separator+file);
            Intent localIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            localIntent.setData(localUri);
            sendBroadcast(localIntent);

            //从相册选择视频
            openVideo();

        }
    }

    //录制视频
    private void recordVideo() {
        try {
            // 开启相机录像应用程序获取并返回录像文件
            mIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            // Uri fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
            //创建文件
            FileUtils.createOrExistsDir(Environment.getExternalStorageDirectory() + "avatar");
            // 獲取拍照的圖片的uri
            mvideoUri = FileUtils.getImageUri(Environment.getExternalStorageDirectory() + "avatar","235.MP4");

            mIntent.putExtra(MediaStore.EXTRA_OUTPUT, mvideoUri);                //指定要保存的位置。
            //captureImageCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, );            //设置拍摄的质量
            mIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);            //限制持续时长,单位秒


            // 指明存储图片或视频的地址URI
            // mIntent.putExtra(MediaStore.EXTRA_OUTPUT, mvideoUri);
            startActivityForResult(mIntent, IntentKV.FLAG_IMAGE_video);
        } catch (Exception e) {
            Toast.makeText(VideoCompressActivity.this, "相机无法启动，请先开启相机权限", Toast.LENGTH_LONG).show();
        }
    }

    //打开手机视频
    private void openVideo() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*"); //String VIDEO_UNSPECIFIED = "video/*";
        Intent wrapperIntent = Intent.createChooser(intent, null);
        startActivityForResult(wrapperIntent, IntentKV.FLAG_IMAGE_video2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (IntentKV.FLAG_IMAGE_video == requestCode && resultCode == RESULT_OK) {
            //相册视频
            Uri data1 = data.getData();
            L.d("返回路径:" + data1 + "," + data1.toString().length());

            try {
                AssetFileDescriptor videoAsset = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                FileInputStream fis = videoAsset.createInputStream();

                mTmpFile = new File(Environment.getExternalStorageDirectory(), "/avatar/" + "VideoFile.mp4");
                FileUtils.createFileByDeleteOldFile(mTmpFile);
                FileOutputStream fos = new FileOutputStream(mTmpFile);

                byte[] buf = new byte[1024];
                int len;
                while ((len = fis.read(buf)) > 0) {
                    fos.write(buf, 0, len);
                }
                fis.close();
                fos.close();
            } catch (IOException io_e) {
                // TODO: handle error
            }

            L.d("录像返回:" + mTmpFile + "," + mTmpFile.length() + "," + mTmpFile);

            // Uri parseUri = Uri.parse("content:/"+mTmpFile.toString());
            // Uri parseUri = Uri.fromFile("content:/"+mTmpFile.toString());
            //File file = new File("content:/"+mTmpFile.toString());
            Uri parseUri = Uri.fromFile(mTmpFile);

            // Uri parseUri = getImageContentUri(this, mTmpFile);

            //获取选中的视频uri路径(4.4以后的版本)
            String vidioUri2 = getPathByUri.getPathByUri4kitkat(this, parseUri);

            //返回的是路径:/storage/emulated/0/avatar/VideoFile.mp4 ,file:///storage/emulated/0/avatar/VideoFile.mp4
            L.d("返回路径uri:" + vidioUri2 + "," + parseUri);
            //LogUtils.d("返回路径uri:" + "," + parseUri);

            //播放视频
            mediasPlayer(vidioUri2);

        } else if (IntentKV.FLAG_IMAGE_video2 == requestCode) {
            //打开录制视频
            if (data == null) {
                ToastUtils.showShort(VideoCompressActivity.this, "相冊视频內容為空");
            } else {
                Uri uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                L.d("uri:" + uri2);

                // cropPic(data.getData());//裁剪圖片,获得的视频路径:content://com.android.providers.media.documents/document/video%3A65973
                L.d("获取视频:" + data.getData());
                Uri uri = data.getData();

                //获取选中的视频uri路径(4.4以后的版本),返回:/storage/emulated/0/DCIM/Camera/V009116.mp4
                String vidioUri = getPathByUri.getPathByUri4kitkat(this, uri);
                L.d("录像返回2:" + vidioUri);
                //播放视频
                mediasPlayer(vidioUri);
            }
        }
    }

    //视频播放
    private void mediasPlayer(String medias) {
        L.d("视频长度:" + FileUtils.getFileLength(medias) + "");
        mTvSize.setText("视频大小(压缩前):" +FileUtils.getFileLength(medias)/ 1024 / 1024 + "M");

        mMediasUrl = medias;
        if (mMediasPlayFlag) {

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            try {
                //根据url获取缩略图
                retriever.setDataSource(medias, new HashMap());
                //获得第一帧图片
                Bitmap bitmap = retriever.getFrameAtTime();
                mVideoplayer.thumbImageView.setImageBitmap(bitmap);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                retriever.release();
            }

           /* String thumb = mProductItemData.getThumb();
            //设置图片
            Picasso.with(this)
                    .load(thumb)  //视频截图
                    .placeholder(R.mipmap.details_icon_play)
                    .into(mVideoplayer.thumbImageView);*/
            sName = "测试视频";

        } else {
            //本地视频的uri
            mMediasUrl = medias;

            if (sThumbPath != null) {

                File file = new File(sThumbPath);
                //设置图片
                Glide.with(this)
                        .load(file)  //视频截图
                        //.placeholder(R.mipmap.details_icon_play)
                        .into(mVideoplayer.thumbImageView);
            } else {
                //直接录制的视频
                MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                mmr.setDataSource(medias);
                Bitmap bitmap = mmr.getFrameAtTime();//获取第一帧图片
                mVideoplayer.thumbImageView.setImageBitmap(bitmap);
                L.d("本地录制视频的第一帧:" + bitmap.toString());
                //mVideoplayer.thumbImageView.setImageResource(R.mipmap.details_icon_play);
            }
        }
        if (sName == null) {
            sName = "VideoFile.mp4";
        }

        L.d("播放视频路径:" + medias);

        // mVideoplayer.setUp("/storage/emulated/0/DCIM/Camera/V009116.mp4",JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"nihao");
        // mVideoplayer.setUp(medias, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, sName);
        mVideoplayer.setUp(medias, JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, sName);
        //文件的路径转为bitmap
        //Bitmap bitmap = ImageUtils.getBitmap(sThumbPath, 1080, 1080);

    }

    //视频压缩
    private void videoCompress(final ReqCallBack reqCallBack ) {
        // mMediasUrl
        if (mMediasUrl != null) {

            long size = 1024 * 1024 * 2;
            L.d("视频长度:" + FileUtils.getFileLength(mMediasUrl) + "");
            if (FileUtils.getFileLength(mMediasUrl) > size) {

                int number = 1245 ;
                number++ ;

                mOutPath = Environment.getExternalStorageDirectory()+"/avatar/" + "V" + number + ".mp4";

                //视频的名称
                sName = "V" + number + ".mp4";
                disposable1 = Observable.create(new ObservableOnSubscribe<Boolean>() {
                    @Override
                    public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                        //boolean success = MediaController
                        //        .getInstance()
                        //        .convertVideo(mMediasUrl,
                        //                mOutPath);

                       // L.d("压缩状态:"+success +"");
                       // e.onNext(success);

                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new io.reactivex.functions.Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean s) throws Exception {
                                // ViewHelper.getInstance().toastCenter(mActivity, s ? "压缩成功" : "压缩失败");
                                if (s.equals("压缩成功")){
                                    L.d("压缩成功");
                                }else if (s.equals("压缩失败")){
                                    L.d("压缩失败");
                                }else {
                                    L.d("压缩");
                                }
                                L.d("视频长度2:" + FileUtils.getFileLength(mOutPath) + "");


                               /* Message msg = new Message();
                                msg.what = 100;
                                msg.obj = mOutPath ;
                                mHandler.sendMessage(msg);*/
                                reqCallBack.reqCompress(mOutPath);

                                //取消进度条
                                //mDialog1.cancel();
                            }
                        });



            } else {
                //Toast.makeText(this, "视频大小超出限制", Toast.LENGTH_SHORT).show();
                L.d("不压缩");

                reqCallBack.reqNoCompress();

            }

        }
    }

    //定义接口
    public interface ReqCallBack {

        void reqCompress(String string);

        void reqNoCompress();

    }

    private void videoCompres() {

        if (mMediasUrl != null) {
            //压缩视频
            videoCompress(new ReqCallBack() {       //压缩
                @Override
                public void reqCompress(String string) {
                    //获取压缩后的视频路径
                    File file = new File(string);

                    L.d("视频大小(压缩后):" + file.length() / 1024 / 1024 + "M");
                    mTvCompressSize.setText("视频大小(压缩后):" + file.length() / 1024 / 1024 + "M");

                }

                @Override
                public void reqNoCompress() {       //不压缩

                    File file = new File(mMediasUrl);
                    L.d("视频大小(不压缩):" + file.length() / 1024 / 1024 + "M");

                }
            });

        }else{
            ToastUtils.show(this, "请选选择视频",2);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediasUrl = null ;
        //mVideoplayer.release(); // 释放视频
    }
}
