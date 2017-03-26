package com.zhuanworld.dev.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.widget.MyPayDialog;

import butterknife.Bind;
import butterknife.OnClick;

public class MyAccountActivity extends BaseActivity {
    @Bind(R.id.title_login_forget_leftImg)
    ImageView mAccountTitleImg;

    @Bind(R.id.title_login_forget_center)
    TextView mAccountTitleCenter;

    @OnClick(R.id.add_bank_card)
    void add_bank_card() {
        startActivity(new Intent(this, AddCardActivity.class));
    }

    @OnClick(R.id.my_account_ti_xian)
    void my_account_ti_xian() {
        MyPayDialog dialog = new MyPayDialog(this, null, new MyPayDialog.OnCustomDialogListener() {
            @Override
            public void back(String name) {
                // TODO: 2017/3/23  

            }
        });
        dialog.show();
        dialog.showKeyboard();
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_my_account;
    }

    @Override
    protected void initData() {
        super.initData();
        initTitle();
    }

    /**
     * 对title进行初始化
     */
    private void initTitle() {
        mAccountTitleCenter.setText("我的账户");
        mAccountTitleImg.setImageResource(R.mipmap.btn_return_white);
    }
}
