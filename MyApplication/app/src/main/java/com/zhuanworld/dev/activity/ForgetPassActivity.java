package com.zhuanworld.dev.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;

import butterknife.Bind;
import butterknife.OnClick;

public class ForgetPassActivity extends BaseActivity {

    @Bind(R.id.re_new_pwd)
    EditText re_new_pwd;
    @Bind(R.id.user_phone)
    EditText user_phone;
    @Bind(R.id.num_code)
    EditText num_code;
    @Bind(R.id.new_pwd)
    EditText new_pwd;

    @OnClick(R.id.back)
    void back() {
        finish();
    }

    @OnClick(R.id.forget_confirm_button)
    void forget_confirm_button() {
        finish();
    }


    @Override
    protected int getContentView() {
        return R.layout.activity_forget_pass;
    }

}
