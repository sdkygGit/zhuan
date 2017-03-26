package com.zhuanworld.dev.fragment;

import android.support.v4.app.Fragment;

import com.zhuanworld.dev.R;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class FragmentIntegral extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_integral;
    }

    public static Fragment newInstance() {
        FragmentIntegral integral = new FragmentIntegral();
        return integral;
    }
}
