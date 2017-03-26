package com.zhuanworld.dev.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.adapter.IncomeDetailsAdapter;
import com.zhuanworld.dev.bean.IncomeDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 收入明细界面
 */
public class IncomeDetailsActivity extends BaseActivity {
    private List<IncomeDetails> mIncomeDetailses = new ArrayList<>();
    private IncomeDetailsAdapter mAdapter;

    @Bind(R.id.title_login_forget_center)
    TextView mIncomeDetailsTitle;

    @Bind(R.id.title_login_forget_leftImg)
    ImageView mIncomeDetailsLeftImg;

    @Bind(R.id.income_details_recycleView)
    RecyclerView mInComeRecycleView;

    @Bind(R.id.total_details)
    TextView mTotalDetails;

    @Bind(R.id.read_details)
    TextView mReadDetails;

    @Bind(R.id.invite_details)
    TextView mInviteDetails;

    @Bind(R.id.read_details_tv)
    TextView mTv;

    @OnClick(R.id.total_details)
    void total_details() {
        mTv.setVisibility(View.GONE);
        mInComeRecycleView.setVisibility(View.VISIBLE);
        mTotalDetails.setSelected(true);
        mReadDetails.setSelected(false);
        mInviteDetails.setSelected(false);
        for (int i = 0; i < 10; i++) {
            IncomeDetails incomeDetails = new IncomeDetails("2016.3.25" + i, "100" + i);
            mIncomeDetailses.add(incomeDetails);
        }
        mInComeRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new IncomeDetailsAdapter(this, mIncomeDetailses);
        mInComeRecycleView.setAdapter(mAdapter);
    }

    @OnClick(R.id.read_details)
    void read_details() {
        mTv.setVisibility(View.VISIBLE);
        mInComeRecycleView.setVisibility(View.GONE);
        mReadDetails.setSelected(true);
        mTotalDetails.setSelected(false);
        mInviteDetails.setSelected(false);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_income_details;
    }

    @Override
    protected void initData() {
        total_details();
        initTitle();
    }

    private void initTitle() {
        mIncomeDetailsTitle.setText("收入明细");
        mIncomeDetailsLeftImg.setImageResource(R.mipmap.btn_return_white);
    }
}
