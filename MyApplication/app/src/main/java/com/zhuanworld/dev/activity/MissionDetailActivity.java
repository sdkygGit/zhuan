package com.zhuanworld.dev.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.adapter.MissionDetailAdapter;
import com.zhuanworld.dev.bean.MissionDetailItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class MissionDetailActivity extends BaseActivity {

    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    @Bind(R.id.title)
    TextView title;

    MissionDetailAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_mission_detail;
    }

    @Override
    protected void initWidget() {
    }

    @Override
    protected void initData() {
        List<MissionDetailItem> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new MissionDetailItem("ssss"));
        }
        mAdapter = new MissionDetailAdapter(MissionDetailActivity.this, list, getImageLoader());
        recycleView.setLayoutManager(new LinearLayoutManager(MissionDetailActivity.this));
        recycleView.setAdapter(mAdapter);
    }
}
