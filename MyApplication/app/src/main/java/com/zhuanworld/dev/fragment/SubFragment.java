package com.zhuanworld.dev.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.adapter.SubItemAdapter;
import com.zhuanworld.dev.bean.Article;
import com.zhuanworld.dev.bean.SubPager;
import com.zhuanworld.dev.view.SubHeaderView;
import com.zhuanworld.dev.viewholder.SubItemViewHolder;
import com.zhuanworld.dev.widget.RefreshLoadLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 通过下拉出现圆圈刷新数据
 * Created by Administrator on 2017/3/15 0015.
 */
public class SubFragment extends BaseFragment {

    //加载数据view
    @Bind(R.id.refreshLoadView)
    RefreshLoadLayout mRefreshLoadLayout;
    RecyclerView mRecycleView;

    Handler mHandler = new Handler();
    SubItemAdapter mAdapter;

    public static SubFragment newInstance() {
        SubFragment subFragment = new SubFragment();
        return subFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sub;
    }

    List<Article> articles;

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mRecycleView = (RecyclerView) root.findViewById(R.id.recycleView);
        mRefreshLoadLayout.setOnLoadDataListener(new RefreshLoadLayout.OnLoadDataListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "刷新完成", Toast.LENGTH_SHORT).show();
                        mRefreshLoadLayout.onComplete();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "加载更多", Toast.LENGTH_SHORT).show();
                        mRefreshLoadLayout.onComplete();
                        mAdapter.switchState(1);
                    }
                }, 500);
            }
        });
    }

    @Override
    protected void initData() {
        articles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            articles.add(new Article("dsfdsfds", "离开过好快改名" + i));
        }
        mRecycleView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new SubItemAdapter(mContext, articles, SubItemAdapter.BOTH_LOAD, getImgLoader());

        List<SubPager> pagers = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            pagers.add(new SubPager("ssss", "http://www.baidu.com"));
        mAdapter.setHeadView(new SubHeaderView(mContext, getImgLoader(), pagers));
        mRecycleView.setAdapter(mAdapter);

    }
}
