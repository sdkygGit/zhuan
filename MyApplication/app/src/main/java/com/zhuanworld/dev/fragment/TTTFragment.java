package com.zhuanworld.dev.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuanworld.dev.R;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public class TTTFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.tab_layout;
    }

    public static TTTFragment newInstance() {
        TTTFragment fragment = new TTTFragment();
        return fragment;
    }

}
