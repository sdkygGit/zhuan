package com.zhuanworld.dev.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.BaseActivity;

import butterknife.OnClick;

/**
 *  获取验证码
 */
public class VertificationCodeActivity extends BaseActivity {
    @OnClick(R.id.vertification_code_next)
    void vertification_code_next() {
        startActivity(new Intent(this,PayPassActivity.class));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_vertification_code;
    }
}
