package com.zhuanworld.dev.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.fragment.BaseFragment;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * 黄金会员
 */
public class ContactGoldFragment extends BaseFragment {
    @Bind(R.id.contact_gold_recycleView)
    RecyclerView mRecyclerView;


    public ContactGoldFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact_gold;
    }

    @Override
    protected void initData() {
        mRecyclerView.setVisibility(View.GONE);
    }
}
