package com.zhuanworld.dev.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;

import butterknife.Bind;

/**
 * 关于赚赚
 */
public class AboutZhuanActivity extends BaseActivity {
    @Bind(R.id.title_login_forget_center)
    TextView mZhuanTitle;

    @Bind(R.id.title_login_forget_leftImg)
    ImageView mZhuanLeftImg;

    @Override
    protected int getContentView() {
        return R.layout.activity_about_zhuan;
    }

    @Override
    protected void initData() {
        initTitle();
    }

    private void initTitle() {
        mZhuanTitle.setText("关于赚赚");
        mZhuanLeftImg.setImageResource(R.mipmap.btn_return_white);
    }
}
