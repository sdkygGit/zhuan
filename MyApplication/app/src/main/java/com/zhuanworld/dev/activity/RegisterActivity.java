package com.zhuanworld.dev.activity;

import android.widget.Button;
import android.widget.EditText;

import com.zhuanworld.dev.R;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @Bind(R.id.getNotifyCodeBtn)
    Button getNotifyCodeBtn;

    @Bind(R.id.user_name)
    EditText user_name;

    @Bind(R.id.num_code)
    EditText num_code;

    @Bind(R.id.user_pwd)
    EditText user_pwd;

    @OnClick(R.id.back)
    void back() {
        finish();
    }

    @OnClick(R.id.login)
    void login() {
        finish();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_register;
    }

}
