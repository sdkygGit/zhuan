package com.zhuanworld.dev.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.MissionDetailActivity;
import com.zhuanworld.dev.adapter.MissionCenterImageAdapter;
import com.zhuanworld.dev.bean.BaiduCpc;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class BaiduCpcFragment extends BaseFragment {

    @Bind(R.id.recycleView)
    RecyclerView recyclerView;

    MissionCenterImageAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_baidu_cpc;
    }

    public static Fragment newInstance() {
        BaiduCpcFragment baiduCpcFragment = new BaiduCpcFragment();
        return baiduCpcFragment;
    }

    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected void initData() {
        List<BaiduCpc> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 2 == 0) {
                BaiduCpc baiduCpc = new BaiduCpc("img", "海洋");
                list.add(baiduCpc);
            } else {
                BaiduCpc baiduCpc = new BaiduCpc("img", "婚纱");
                list.add(baiduCpc);
            }
        }
        mAdapter = new MissionCenterImageAdapter(list, mContext, getImgLoader());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mAdapter.setListener(new MissionCenterImageAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                startActivity(new Intent(mContext, MissionDetailActivity.class));
            }
        });

    }
}
