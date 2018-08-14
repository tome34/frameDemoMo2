package com.example.tome.module_common.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.tome.module_common.R;
import com.example.tome.module_common.activity.zxing.SecondActivity;
import com.example.tome.module_common.activity.zxing.ThreeActivity;
import com.example.tome.module_common.utils.CheckPermissionUtils;
import com.example.tome.module_common.utils.ImageUtil;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import java.util.List;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

//https://github.com/yipianfengye/android-zxingLibrary
public class ZxingActivity extends AppCompatActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    public Button button1 = null;
    public Button button2 = null;
    public Button button3 = null;
    public Button button4 = null;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        //初始化组件
        initView();
        //初始化权限
        initPermission();
    }



    private void initView() {
        button1 =  findViewById(R.id.button1);
        button2 =  findViewById(R.id.button2);
        button3 =  findViewById(R.id.button3);
        button4 =  findViewById(R.id.button4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
    }

    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(this);
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }

    /**
     * 处理二维码扫描结果
     */
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
       // super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_CODE){
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(ZxingActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }else if (requestCode == REQUEST_IMAGE){
            //选择系统图片并解析
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(ZxingActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(ZxingActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else if (requestCode == REQUEST_CAMERA_PERM){
            Toast.makeText(ZxingActivity.this, "从设置页面返回...", Toast.LENGTH_SHORT)
                 .show();
        }

    }

    /**
     * EsayPermissions接管权限处理逻辑
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode,@NonNull List<String> perms) {
        Toast.makeText(this, "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode,@NonNull List<String> perms) {
        Toast.makeText(this, "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this)
                .setTitle("权限申请")
                .setPositiveButton("确认")
                .setNegativeButton("取消")
                .setRationale("当前App需要申请camera权限,需要打开设置页面么?")
                .setRequestCode(REQUEST_CAMERA_PERM)
                .build()
                .show();
        }
    }



    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    public void cameraTask(int viewId) {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
           if (viewId == 1){
               mIntent = new Intent(getApplication(), CaptureActivity.class);
               startActivityForResult(mIntent, REQUEST_CODE);
           }else if (viewId == 2){
               mIntent = new Intent(ZxingActivity.this, SecondActivity.class);
               startActivityForResult(mIntent, REQUEST_CODE);
           }
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求camera权限",
                REQUEST_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }




    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1){
            cameraTask(1);

        }else if (v.getId() == R.id.button3){
            cameraTask(2);

        }else if (v.getId() == R.id.button2){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_IMAGE);
        }else if (v.getId() == R.id.button4){
            Intent intent = new Intent(ZxingActivity.this, ThreeActivity.class);
            startActivity(intent);
        }else {

        }
    }

}
