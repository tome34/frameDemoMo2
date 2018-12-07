package com.example.tome.projectCore.base.mvp;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import com.example.tome.core.R;
import com.example.tome.core.base.mvp.BaseVpActivity;
import com.example.tome.core.base.mvp.inter.IPresenter;
import com.example.tome.core.base.mvp.inter.IView;
import com.example.tome.core.util.L;
import com.example.tome.projectCore.dialog.MyAlertDialog;

/**
 * @Created by TOME .
 * @时间 2018/5/17 17:11
 * @描述 ${permissionsdispatcher 处理权限管理}
 */

public abstract class BaseVpPermissionActivity<V extends IView, P extends IPresenter<V>> extends BaseVpActivity<V, P> {

    /***照相机权限*/
    public static final int PERMISSION_CAMERA = 10001;

    /**文件管理权限*/
    public static final int PERMISSION_STORAGE = 10002;

    /***电话权限*/
    public static final int PERMISSION_PHONE = 10003;



    private boolean isShouldShow=false;
    public MyAlertDialog dialog;

    /**判断是否含有当前权限*/
    public boolean getPermission(String permission,int requestCode){
        L.d("申请权限!isGranted(permission)="+!isGranted(permission));
        L.d("申请权限isMarshmallow="+isMarshmallow());

        if (!isGranted(permission)&&isMarshmallow()){
            //当前权限未授权，并且系统版本为6.0以上，需要申请权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,permission)){
                isShouldShow = true;
                L.d("申请权限="+permission);
                ActivityCompat.requestPermissions(this,new String[]{permission},requestCode);
            }else{
                L.d("没有申请权限="+permission);
                isShouldShow = false;
                showPresmissionDialog(requestCode);
            }
            return false;
        }
        return true;
    }

    /**判断当前是否已经授权*/
    protected boolean isGranted(String permission){
        int granted = ActivityCompat.checkSelfPermission(this, permission);
        return granted == PackageManager.PERMISSION_GRANTED;
    }

    /**判断当前版本为6.0以上*/
    protected boolean isMarshmallow(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions!=null&&permissions.length>0&&grantResults!=null&&grantResults.length>0){
            int grantResult = grantResults[0];
            String permission = permissions[0];
            if (grantResult==PackageManager.PERMISSION_DENIED&&!ActivityCompat.shouldShowRequestPermissionRationale(this,permission)&&!isShouldShow){
                //没有获取到权限,并且用户选择了不在提醒
                showPresmissionDialog(requestCode);
            }
        }
    }


    private void showPresmissionDialog(int requestCode){


       // dialog = new MyAlertDialog("权限设置",initView(requestCode),"取消",new String[]{"去设置"},null,this, AlertView.Style.Alert, IFlag.FLAG_SET_PERMISSION,this);
        dialog = new MyAlertDialog(this).builder().setTitle("权限设置")
                                        .setMsg(initView(requestCode)).setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                }).setPositiveButton("去设置", new MyAlertDialog.OnClickListenerAlertDialog() {
                    @Override
                    public void onClick(View v, Dialog dialog) {
                        //去设置
                        dialog.dismiss();
                        Uri packageURI = Uri.parse("package:"+getPackageName());
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,packageURI);
                        startActivity(intent);
                    }
                });

        dialog.show();

    }


    public  String initView(int requestCode) {
        String message = "";
        if (requestCode== BaseVpPermissionActivity.PERMISSION_CAMERA){
            //照相机权限
            message = getString(R.string.home_permission_camera);
        }else if(requestCode == BaseVpPermissionActivity.PERMISSION_PHONE){
            //电话权限
            message = getString(R.string.home_permission_phone);
        }else if(requestCode == BaseVpPermissionActivity.PERMISSION_STORAGE){
            //文件操作权限
            message = getString(R.string.home_permission_storage);
        }else{
            message = getString(R.string.home_permission_default);
        }
        return message;
//        return requestCode+"";
    }
}
