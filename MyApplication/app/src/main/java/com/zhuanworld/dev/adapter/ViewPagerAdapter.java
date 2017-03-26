package com.zhuanworld.dev.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zhuanworld.dev.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        if (fragmentList != null) {
            mFragmentList.addAll(fragmentList);
        }
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = null;
        if (position >= 0 && position < getCount()) {
            fragment = mFragmentList.get(position);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    public void update(List<BaseFragment> fragmentList) {
        mFragmentList.clear();
        if (fragmentList != null) {
            mFragmentList.addAll(fragmentList);
        }
        notifyDataSetChanged();
    }
}

