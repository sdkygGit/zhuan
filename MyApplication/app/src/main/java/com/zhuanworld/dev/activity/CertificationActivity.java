package com.zhuanworld.dev.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.BaseActivity;

/**
 * 实名认证
 */
public class CertificationActivity extends BaseActivity {
    private ImageView mTitleLeftImg;
    private TextView mTitleCenterTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        initView();
    }

    private void initView() {
        mTitleLeftImg = (ImageView) findViewById(R.id.title_login_forget_leftImg);
        mTitleCenterTv = (TextView) findViewById(R.id.title_login_forget_center);

        mTitleLeftImg.setImageResource(R.mipmap.btn_return_white);
        mTitleCenterTv.setText("实名认证");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_certification;
    }
}
