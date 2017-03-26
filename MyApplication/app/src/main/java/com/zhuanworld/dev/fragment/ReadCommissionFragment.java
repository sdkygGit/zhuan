package com.zhuanworld.dev.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.adapter.ReadCommissionDataAdapter;
import com.zhuanworld.dev.bean.ReadCommission;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class ReadCommissionFragment extends BaseFragment {

    ReadCommissionDataAdapter mAdapter;

    @Bind(R.id.recycleView)
    RecyclerView recyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_read_commission;
    }

    @Override
    protected void initData() {
        List<ReadCommission> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ReadCommission readCommission = new ReadCommission("5.0", "3.16");
            list.add(readCommission);
        }
        mAdapter = new ReadCommissionDataAdapter(mContext, list);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

    }
}
