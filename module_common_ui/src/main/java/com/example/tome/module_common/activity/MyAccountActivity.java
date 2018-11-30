package com.example.tome.module_common.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.projectCore.helper.ImageLoaderHelper;
import com.example.tome.core.util.L;
import com.example.tome.core.widget.CircularImageView;
import com.example.tome.module_common.R;
import com.example.tome.module_common.R2;
import com.example.tome.module_common.widget.ImageAlertDialog;
import java.io.File;

public class MyAccountActivity extends BaseVcActivity implements ImageAlertDialog.OnImageSelectResult, View.OnClickListener {

    @BindView(R2.id.civ_member_icon)
    CircularImageView mCivMemberIcon;
    @BindView(R2.id.iv_member_image_arrow)
    ImageView mIvMemberImageArrow;
    @BindView(R2.id.llyt_member_icon)
    LinearLayout mLlytMemberIcon;
    private ImageAlertDialog selectImageDialog;
    private int dissHudflag = 0;
    private int dissHud = 2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_account;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView() {
        mLlytMemberIcon.setOnClickListener(this);
        selectImageDialog = new ImageAlertDialog(this);
        selectImageDialog.setImageSelectListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (selectImageDialog != null && resultCode == RESULT_OK) {
            selectImageDialog.onActivityResult(requestCode, data);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void getImage(File fileName) {
        // showHUD();
        dissHud = 3;
        L.d("getImage =fileName = " + fileName);
       // 加载本地图片 ,待封装图片
       // Glide.with(this).load(fileName).into(mCivMemberIcon);
        ImageLoaderHelper.getInstance().load(this, fileName, mCivMemberIcon);
    }

    @Override
    public void onClick(View v) {
       /* if (!getPermission(Manifest.permission.CAMERA, PERMISSION_CAMERA)) {
            return;
        }
        if (!getPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, PERMISSION_STORAGE)) {
            return;
        }
        if (!getPermission(Manifest.permission.READ_EXTERNAL_STORAGE, PERMISSION_STORAGE)) {
            return;
        }*/
        if (v.getId() == R.id.llyt_member_icon){
            selectImageDialog.show();
        }
    }

}
