package com.zhuanworld.dev.fragment;

import android.view.View;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.widget.TweetPicturesPreviewer;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
public class TweetPublishFragment extends BaseFragment implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tweet_publish;
    }

    // 用于拦截后续的点击事件
    private long mLastClickTime;

    @Bind(R.id.recycler_images)
    TweetPicturesPreviewer mLayImages;

    @OnClick({R.id.iv_picture})
    @Override
    public void onClick(View v) {
        long nowTime = System.currentTimeMillis();
        if ((nowTime - mLastClickTime) < 1000)
            return;
        mLastClickTime = nowTime;
        switch (v.getId()) {
            case R.id.iv_picture:
                mLayImages.onLoadMoreClick();
                break;
        }
    }


}
