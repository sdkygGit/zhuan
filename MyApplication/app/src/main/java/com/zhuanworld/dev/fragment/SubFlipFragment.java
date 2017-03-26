package com.zhuanworld.dev.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.BackActivity;
import com.zhuanworld.dev.adapter.SubFilpItemAdapter;
import com.zhuanworld.dev.adapter.SubItemAdapter;
import com.zhuanworld.dev.bean.Article;
import com.zhuanworld.dev.bean.SubPager;
import com.zhuanworld.dev.view.EmptyLayout;
import com.zhuanworld.dev.view.SubHeaderView;
import com.zhuanworld.dev.widget.PullToRefreshBase;
import com.zhuanworld.dev.widget.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class SubFlipFragment extends BaseFragment {

    //    @Bind(R.id.pull_refresh_list)
    private PullToRefreshListView mPullRefreshListView;

    ListView mListView;

    Handler mHandler = new Handler();
    SubFilpItemAdapter mAdapter;

    public static SubFlipFragment newInstance(int index) {
        SubFlipFragment subFragment = new SubFlipFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        subFragment.setArguments(bundle);
        return subFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sub_flip;
    }

    List<Article> articles;

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mPullRefreshListView = (PullToRefreshListView) root.findViewById(R.id.pull_refresh_list);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String label = DateUtils.formatDateTime(mContext, System.currentTimeMillis(),
                                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                        // 更新label
                        refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                        AppContext.showToast("onPullDownToRefresh!");
                        mPullRefreshListView.onRefreshComplete();
                        List<Article> list = new ArrayList<>();
                        for (int i = 0; i < 16; i++) {
                            list.add(new Article("dsfdsfds", "离开过好快改名" + i));
                        }
                        mAdapter.addData(list, false);

                    }
                }, 500);
            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String label = DateUtils.formatDateTime(mContext, System.currentTimeMillis(),
                                DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
//				// Update the LastUpdatedLabel
                        refreshView.getLoadingLayoutProxy(false, false).setLastUpdatedLabel(label);
                        mPullRefreshListView.onRefreshComplete();
                        List<Article> list = new ArrayList<>();
                        for (int i = 0; i < 16; i++) {
                            list.add(new Article("dsfdsfds", "离开过好快改名" + i));
                        }
                        mAdapter.addData(list, false);
                    }
                }, 500);
            }
        });

        mListView = mPullRefreshListView.getRefreshableView();
        mListView.setDivider(getResources().getDrawable(R.drawable.listview_divider));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(mContext, BackActivity.class);
                intent.putExtra(BackActivity.BUNDLE_KEY_PAGE, 26);
                Bundle bundle = new Bundle();
                bundle.putString(BrowserFragment.BROWSER_KEY, "http://www.baidu.com");
                intent.putExtra(BackActivity.BUNDLE_KEY_ARGS, bundle);
                startActivity(intent);
            }
        });

        EmptyLayout tv = new EmptyLayout(mContext);
        tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        tv.setBackgroundColor(Color.parseColor("#EEEEEE"));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "重新加载", Toast.LENGTH_SHORT).show();
            }
        });
        mPullRefreshListView.setEmptyView(tv);
    }

    @Override
    protected void initData() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                testData();
            }
        }, 500);
    }

    void testData() {
        if (mContext != null) {
            articles = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                articles.add(new Article("dsfdsfds", "离开过好快改名" + i));
            }
            mAdapter = new SubFilpItemAdapter(mContext, articles, SubItemAdapter.FOOTER_LOAD, getImgLoader());

            List<SubPager> pagers = new ArrayList<>();
            for (int i = 0; i < 5; i++)
                pagers.add(new SubPager("ssss", "http://www.baidu.com"));

            int index = getArguments().getInt("index");
            if (index == 0) {
                SubHeaderView headerView = new SubHeaderView(mContext, getImgLoader(), pagers);
                mListView.addHeaderView(headerView);
            }
            mListView.setAdapter(mAdapter);
            mListView.setDivider(null);
        }

    }
}
