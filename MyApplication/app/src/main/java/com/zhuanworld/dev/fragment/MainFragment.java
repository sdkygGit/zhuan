package com.zhuanworld.dev.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.BackActivity;
import com.zhuanworld.dev.activity.TweetPublishActivity;
import com.zhuanworld.dev.bean.SimpleBackPage;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public class MainFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    void fabiao() {

        Bundle bundle = new Bundle();
        bundle.putString("browser_url", "http://www.baidu");
        Intent intent = new Intent(getActivity(), BackActivity.class);
        intent.putExtra(BackActivity.BUNDLE_KEY_PAGE, 26);
        intent.putExtra(BackActivity.BUNDLE_KEY_ARGS, bundle);
        startActivity(intent);
    }

    @Override
    protected void initWidget(View root) {


    }
}
