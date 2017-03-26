package com.zhuanworld.dev.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加银行卡
 */
public class AddCardActivity extends BaseActivity {
    @Bind(R.id.title_login_forget_center)
    TextView mTitle;
    @Bind(R.id.title_login_forget_leftImg)
    ImageView mLeftImg;

    @OnClick(R.id.add_bank_card_next)
    void add_bank_card_next() {
        startActivity(new Intent(this, VertificationCodeActivity.class));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_add_card;
    }

    @Override
    protected void initData() {
        initTitle();
    }

    private void initTitle() {
        mTitle.setText("添加银行卡");
        mLeftImg.setImageResource(R.mipmap.btn_return_white);
    }
}
