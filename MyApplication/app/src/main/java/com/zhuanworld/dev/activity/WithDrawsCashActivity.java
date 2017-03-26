package com.zhuanworld.dev.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;

import butterknife.Bind;

/**
 * 兑换提现
 */
public class WithDrawsCashActivity extends BaseActivity {
    @Bind(R.id.title_login_forget_center)
    TextView mWithDrawsTitle;

    @Bind(R.id.title_login_forget_leftImg)
    ImageView mWithDrawsLeft;

    @Bind(R.id.bank_card_number1)
    EditText mBankNumber1;

    @Bind(R.id.bank_card_number2)
    EditText mBankNumber2;

    @Bind(R.id.bank_card_number3)
    EditText mBankNumber3;

    @Bind(R.id.bank_card_number4)
    EditText mBankNumber4;

    @Bind(R.id.bank_card_number5)
    EditText mBankNumber5;

    @Override
    protected int getContentView() {
        return R.layout.activity_with_draws_cash;
    }

    @Override
    protected void initData() {
        initTitle();
    }

    private void initTitle() {
        mWithDrawsLeft.setImageResource(R.mipmap.btn_return_white);
        mWithDrawsTitle.setText("兑换提现");
        mBankNumber1.setText("123");
        mBankNumber2.setText("4567");
        mBankNumber3.setText("4567");
        mBankNumber4.setText("4567");
        mBankNumber5.setText("4567");
        //设置隐藏密码
        mBankNumber1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mBankNumber2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mBankNumber3.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mBankNumber4.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }
}
