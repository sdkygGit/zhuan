package com.zhuanworld.dev.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;

import butterknife.Bind;

/**
 * 联系我们
 */
public class ContactUsActivity extends BaseActivity {
    @Bind(R.id.title_login_forget_center)
    TextView mContactUsTitle;

    @Bind(R.id.title_login_forget_leftImg)
    ImageView mContactUsLeftImg;

    @Override
    protected int getContentView() {
        return R.layout.activity_contact_us;
    }

    @Override
    protected void initData() {
        initTitle();
    }

    private void initTitle() {
        mContactUsTitle.setText("联系我们");
        mContactUsLeftImg.setImageResource(R.mipmap.btn_return_white);
    }
}
