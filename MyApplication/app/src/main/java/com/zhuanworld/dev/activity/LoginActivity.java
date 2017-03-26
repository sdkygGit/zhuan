package com.zhuanworld.dev.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuanworld.dev.BaseApplication;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.User;
import com.zhuanworld.dev.helper.SharedPreferencesHelper;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 登陆界面
 */

public class LoginActivity extends BaseActivity {

    @Bind(R.id.user_name)
    EditText user_name;

    @Bind(R.id.password)
    EditText passwordE;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.login)
    void login() {
        String phoneNumber = user_name.getText().toString().trim();
        String password = passwordE.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(LoginActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

//        User user = new User();
//        user.setName("phone");
//        user.setId(1);
//        SharedPreferencesHelper.save(LoginActivity.this, user);
//        hideSoftKeyboard();
//        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.register)
    void register() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @OnClick(R.id.forget_password)
    void forget_password() {
        startActivity(new Intent(LoginActivity.this, ForgetPassActivity.class));
    }

//    /**
//     * 隐藏软键盘
//     */
//    protected void hideSoftKeyboard() {
//        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN && getCurrentFocus() != null) {
//            mInputMethodManager.hideSoftInputFromWindow(
//                    getCurrentFocus().getWindowToken(),
//                    InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }
}
