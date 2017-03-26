package com.zhuanworld.dev.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.BaseActivity;
import com.zhuanworld.dev.activity.SelectImageActivity;
import com.zhuanworld.dev.bean.Image;
import com.zhuanworld.dev.bean.SelectOptions;

import butterknife.Bind;
import butterknife.OnClick;

public class PersonalDataActivity extends BaseActivity implements View.OnClickListener{
    private ImageView mTitleLeftImg, mMyIcon;
    private TextView mTitleCenterTv;
    private RelativeLayout mMyNickName,mCertification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        initView();
        setListener();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_personal_data;
    }

    private void initView() {
        mTitleLeftImg = (ImageView) findViewById(R.id.title_login_forget_leftImg);
        mTitleCenterTv = (TextView) findViewById(R.id.title_login_forget_center);
        mMyIcon = (ImageView) findViewById(R.id.my_icon_personal_data);
        mMyNickName = (RelativeLayout) findViewById(R.id.my_nickName_personal_data);
        mCertification = (RelativeLayout) findViewById(R.id.my_certification_personal_data);

        mTitleCenterTv.setText("个人资料");
        mTitleLeftImg.setImageResource(R.mipmap.btn_return_white);
    }

    private void setListener() {
        mCertification.setOnClickListener(this);
        mMyNickName.setOnClickListener(this);
        mMyIcon.setOnClickListener(this);
        mTitleLeftImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_certification_personal_data:
                startActivity(new Intent(this,CertificationActivity.class));
                break;
            case R.id.my_icon_personal_data:
                selsctMyIcon();
                break;
            case R.id.my_nickName_personal_data:
                startActivity(new Intent(this,ChangeNickNameActivity.class));
                break;
            case R.id.title_login_forget_leftImg:
                break;
        }

    }

    /**
     * 选择作为头像的图片
     */
    private void selsctMyIcon() {
        SelectImageActivity.show(this, new SelectOptions.Builder()
                .setHasCam(true)
                .setSelectCount(1)
                .setCallback(new SelectOptions.Callback() {
                    @Override
                    public void doSelected(String[] images) {
                        Toast.makeText(PersonalDataActivity.this, "选择了 :" + images[0], Toast.LENGTH_SHORT).show();
                    }
                }).build());
    }
}
