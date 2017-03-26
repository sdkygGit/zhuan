package com.zhuanworld.dev.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.R;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/18 0018.
 * 设置
 */
public class MeSettingActivity extends BaseActivity {
    @OnClick(R.id.back)
    void back() {
        startActivity(new Intent(this,MeSettingActivity.class));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_me_setting;
    }

    @OnClick(R.id.motify_login_pwd)
    void motify_login_pwd() {
       startActivity(new Intent(this, MotifyLoginPwdActivity.class));

    }

    @OnClick(R.id.setting_transaction_pwd)
    void setting_transaction_pwd() {
        startActivity(new Intent(this, SetTransactionPwdActivity.class));
    }

    @OnClick(R.id.change_phone_num)
    void change_phone_num() {
       startActivity(new Intent(this, ChangePhoneActivity.class));
    }

    @OnClick(R.id.pay_setting)
    void pay_setting() {
       startActivity(new Intent(this, PaySettingActivity.class));

    }

    @OnClick(R.id.clearn_cache)
    void clearn_cache() {

    }

    @OnClick(R.id.feedback)
    void feedback() {
        startActivity(new Intent(this, FeedBackActivity.class));

    }

    @OnClick(R.id.privacy_agreement)
    void privacy_agreement() {
        startActivity(new Intent(this, PrivacyAgreementActivity.class));
    }

    @OnClick(R.id.check_update)
    void check_update() {
            AppContext.showToast("检测更新");
    }


    @OnClick(R.id.exit_login)
    void exit_login() {
        AppContext.showToast("注销账户");
    }

}
