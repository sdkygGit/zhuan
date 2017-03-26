package com.zhuanworld.dev.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.fragment.TweetPublishFragment;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
public class TweetPublishActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_tweet_publish;
    }

    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "unchecked", "ResultOfMethodCallIgnored"})
    @Override
    protected void initWidget() {
        super.initWidget();
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle == null) bundle = new Bundle();
        // Read other data

        TweetPublishFragment fragment = new TweetPublishFragment();
        // init the args bounds
        fragment.setArguments(bundle);
        FragmentTransaction trans = getSupportFragmentManager()
                .beginTransaction();
        trans.replace(R.id.activity_tweet_publish, fragment);
        trans.commit();
    }

}
