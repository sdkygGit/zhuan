package com.zhuanworld.dev.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuanworld.dev.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 常见问题
 */
public class QuestionActivity extends BaseActivity {
    private ImageView mQuestion1Icon,mQuestion2Icon, mQuestion3Icon;
    public int mClickNum1 = 0,mClickNum2 = 0,mClickNum3 = 0;

    @Bind(R.id.title_login_forget_center)
    TextView mQuestionTitle;

    @Bind(R.id.title_login_forget_leftImg)
    ImageView mQuestionLeftImg;

    @Bind(R.id.question1)
    RelativeLayout mQuestion1;

    @Bind(R.id.question1_details)
    LinearLayout mQuestion1_details;

    @Bind(R.id.question2)
    RelativeLayout mQuestion2;

    @Bind(R.id.question2_details)
    LinearLayout mQuestion_details;

    @Bind(R.id.question3)
    RelativeLayout mQuestion3;

    @Bind(R.id.question3_details)
    LinearLayout mQuestion3_details;

    @OnClick(R.id.question1)
    void question1() {
        mClickNum1++;
        if (mClickNum1 % 2 == 1) {
            mQuestion1Icon.setRotation(90);
            mQuestion1_details.setVisibility(View.VISIBLE);
        } else {
            mQuestion1Icon.setRotation(360);
            mQuestion1_details.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.question2)
    void question2() {
        mClickNum2++;
        if (mClickNum2 % 2 == 1) {
            mQuestion2Icon.setRotation(90);
            mQuestion_details.setVisibility(View.VISIBLE);
        } else {
            mQuestion2Icon.setRotation(360);
            mQuestion_details.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.question3)
    void question3() {
        mClickNum3++;
        if (mClickNum3 % 2 == 1) {
            mQuestion3Icon.setRotation(90);
            mQuestion3_details.setVisibility(View.VISIBLE);
        } else {
            mQuestion3Icon.setRotation(360);
            mQuestion3_details.setVisibility(View.GONE);
        }
    }



    @Override
    protected int getContentView() {
        return R.layout.activity_question;
    }

    @Override
    protected void initData() {
        initTitle();
    }

    private void initTitle() {
        mQuestion1Icon = (ImageView) findViewById(R.id.question1_icon);
        mQuestion2Icon = (ImageView) findViewById(R.id.question2_icon);
        mQuestion3Icon = (ImageView) findViewById(R.id.question3_icon);
        mQuestionTitle.setText("常见问题");
        mQuestionLeftImg.setImageResource(R.mipmap.btn_return_white);
    }
}
