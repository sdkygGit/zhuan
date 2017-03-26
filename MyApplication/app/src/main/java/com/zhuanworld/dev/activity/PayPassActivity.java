package com.zhuanworld.dev.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.BaseActivity;
import com.zhuanworld.dev.activity.MyAccountActivity;

import butterknife.Bind;
import butterknife.OnClick;

public class PayPassActivity extends BaseActivity {
    @Bind(R.id.title_login_forget_center)
    TextView mPayPassTitle;

    @Bind(R.id.title_login_forget_leftImg)
    ImageView mPayPassLeftImg;
    @OnClick(R.id.input_pay_pass_next)
    void input_pay_pass_next() {
        startActivity(new Intent(this, MyAccountActivity.class));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_pay_pass;
    }

    @Override
    protected void initData() {
        initTilte();
    }

    private void initTilte() {
        mPayPassTitle.setText("请输入支付密码");
        mPayPassLeftImg.setImageResource(R.mipmap.btn_return_white);
    }
}
