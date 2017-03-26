package com.zhuanworld.dev.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.account.AccountHelper;
import com.zhuanworld.dev.app.AppOperator;
import com.zhuanworld.dev.bean.User;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
public class LaunchActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.app_start;
    }

    @Override
    protected void initData() {
        super.initData();
//        User user = AccountHelper.getUser();
//        if (user == null) {
//            mHandle.sendEmptyMessageDelayed(0, 800);
//        } else {
        mHandle.sendEmptyMessageDelayed(1, 800);
//        }
    }

    Handler mHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    Intent intent = new Intent(LaunchActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;

                case 1: {
                    Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                break;
            }
        }
    };

}
