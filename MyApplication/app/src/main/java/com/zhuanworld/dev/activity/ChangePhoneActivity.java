package com.zhuanworld.dev.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.BaseActivity;

import butterknife.Bind;

/**
 * 更换手机号
 */
public class ChangePhoneActivity extends BaseActivity {
    @Bind(R.id.title_login_forget_leftImg)
    ImageView mTitleLeftImg;

    @Bind(R.id.title_login_forget_center)
    TextView mTitleCenterTv;

    @Bind(R.id.title_login_forget_rightTv)
    TextView mTitleRightTv;


    @Override
    protected int getContentView() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void initData() {
        super.initData();
        initView();
    }

    private void initView() {
            mTitleLeftImg.setImageResource(R.mipmap.btn_return_white);
            mTitleCenterTv.setText("更换手机号");
            mTitleRightTv.setText("完成");
        }
}
