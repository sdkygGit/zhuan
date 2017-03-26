package com.zhuanworld.dev.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.Image;
import com.zhuanworld.dev.bean.SelectImageContract;
import com.zhuanworld.dev.bean.SelectOptions;
import com.zhuanworld.dev.callback.PermissionGrantedCallBack;
import com.zhuanworld.dev.fragment.SelectFragment;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/3/10 0010.
 * 选择图片
 */
public class SelectImageActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {
    public static final int RC_CAMERA_PERM = 0x03;
    private static final int RC_EXTERNAL_STORAGE = 0x04;
    private static SelectOptions mSelectOption;

    PermissionGrantedCallBack mCallBack;

    @Override
    protected int getContentView() {
        return R.layout.activity_select_image;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        requestExternalStorage();
    }

    @AfterPermissionGranted(RC_CAMERA_PERM)
    public boolean requestCamera() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            return true;
        } else {
            EasyPermissions.requestPermissions(this, "拍照需要摄像头权限", RC_CAMERA_PERM, Manifest.permission.CAMERA);
            return false;
        }
    }

    @AfterPermissionGranted(RC_EXTERNAL_STORAGE)
    public void requestExternalStorage() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            handleView();
        } else {
            EasyPermissions.requestPermissions(this, "获取图片需要SD卡权限", RC_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if (mCallBack != null) {
            mCallBack.onPermissionsDenied(requestCode, perms);
        }
    }

    public void setPermissionCallBack(PermissionGrantedCallBack callBack) {
        this.mCallBack = callBack;
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == RC_EXTERNAL_STORAGE)
            Toast.makeText(SelectImageActivity.this, "请开启访问SD卡权限", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(SelectImageActivity.this, "请开启手机相机权限", Toast.LENGTH_SHORT).show();
    }

    void handleView() {
        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_content, SelectFragment.newInstance(mSelectOption))
                    .commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(Context context, SelectOptions build) {
        mSelectOption = build;
        context.startActivity(new Intent(context, SelectImageActivity.class));
    }

    public void selectComplete(List<com.zhuanworld.dev.bean.Image> paths) {
        if (paths != null && paths.size() > 0) {
            ArrayList<String> imgPaths = new ArrayList<>();
            for (Image image : paths)
                imgPaths.add(image.getPath());
            Intent intent = new Intent();
            intent.putStringArrayListExtra("paths", imgPaths);
            setResult(RESULT_OK, intent);
            finish();
        }

    }

}
