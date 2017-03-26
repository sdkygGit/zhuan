package com.zhuanworld.dev.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.BaseActivity;

/**
 * 修改昵称
 */
public class ChangeNickNameActivity extends BaseActivity {
    private ImageView mTitleLeftImg;
    private TextView mTitleCenterTv,mTitleRightTv;
    private EditText mChangeNick;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_nick_name);
        initView();
    }

    private void initView() {
        mTitleLeftImg = (ImageView) findViewById(R.id.title_login_forget_leftImg);
        mTitleCenterTv = (TextView) findViewById(R.id.title_login_forget_center);
        mTitleRightTv = (TextView) findViewById(R.id.title_login_forget_rightTv);

        mTitleLeftImg.setImageResource(R.mipmap.btn_return_white);
        mTitleCenterTv.setText("更改昵称");
        mTitleRightTv.setText("保存");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_change_nick_name;
    }
}
