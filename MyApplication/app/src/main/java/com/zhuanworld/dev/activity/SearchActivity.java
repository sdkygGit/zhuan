package com.zhuanworld.dev.activity;

import com.zhuanworld.dev.R;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class SearchActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @OnClick(R.id.searchTv)
    void search() {

    }

    @OnClick(R.id.back)
    void back() {
        finish();
    }
}
