package com.zhuanworld.dev.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.BaseActivity;
import com.zhuanworld.dev.activity.ContactsActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 关于我们
 */
public class AboutUsActivity extends BaseActivity {
    @Bind(R.id.title_login_forget_center)
    TextView mTitleCenterTv;

    @Bind(R.id.title_login_forget_leftImg)
    ImageView mTitleLeftImg;


    @OnClick(R.id.about_us_contact_us)
    void contact_us() {
        startActivity(new Intent(this,ContactUsActivity.class));
    }

    @OnClick(R.id.about_us_zhuan_zhuan)
    void about_zhuan() {
        startActivity(new Intent(this,AboutZhuanActivity.class));
    }

    @OnClick(R.id.about_us_question)
    void about_us_question() {
        startActivity(new Intent(this,QuestionActivity.class));
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initData() {
        mTitleCenterTv.setText("关于我们");
        mTitleLeftImg.setImageResource(R.mipmap.btn_return_white);
    }
}
