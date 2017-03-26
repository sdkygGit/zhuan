package com.zhuanworld.dev.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.MainActivity;
import com.zhuanworld.dev.activity.NavItemModifyDialogActivity;
import com.zhuanworld.dev.activity.SearchActivity;
import com.zhuanworld.dev.app.AppOperator;
import com.zhuanworld.dev.bean.SubTab;
import com.zhuanworld.dev.utils.StreamUtil;
import com.zhuanworld.dev.utils.TDevice;
import com.zhuanworld.dev.utils.TLog;
import com.zhuanworld.dev.widget.TabPickerView;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class DynamicTabFragment extends BaseTitleFragment {

    @Bind(R.id.layout_tab)
    TabLayout mLayoutTab;
    @Bind(R.id.view_tab_picker)
    TabPickerView mViewTabPicker;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.iv_arrow_down)
    ImageView mViewArrowDown;

    @Bind(R.id.title_tv)
    TextView titleTv;

    @Bind(R.id.rightIcon)
    ImageView rightIcon;

    private MainActivity activity;

    List<SubTab> tabs;
    private FragmentPagerAdapter mAdapter;
    private Fragment mCurFragment;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_dynamictab;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        titleTv.setText("赚赚");

        tabs = new ArrayList<>();

        initTabData();

        mViewPager.setAdapter(mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
//                return SubFragment.newInstance();
                return SubFlipFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return tabs.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabs.get(position).getName();
            }

            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);
                if (mCurFragment == null) {
                    commitUpdate();
                }
                mCurFragment = (Fragment) object;
            }

            @Override
            public int getItemPosition(Object object) {
                return PagerAdapter.POSITION_NONE;
            }

        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mAdapter.commitUpdate();
                }
            }
        });
        mLayoutTab.setupWithViewPager(mViewPager);
        mLayoutTab.setSmoothScrollingEnabled(true);

        mViewTabPicker.initData(tabs);
        mViewTabPicker.setOnTabItemListener(new TabPickerView.OnTabItemListener() {
            @Override
            public void onItemClick(int pos) {
                mViewPager.setCurrentItem(pos);
                mViewArrowDown.setEnabled(false);
                mViewArrowDown.animate()
                        .rotation(0)
                        .setDuration(380)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
                                mViewArrowDown.setRotation(0);
                                mViewArrowDown.setEnabled(true);
                            }
                        });
            }

            @Override
            public void onHide() {
                mViewArrowDown.setEnabled(false);
                mViewArrowDown.animate()
                        .rotation(0)
                        .setDuration(380)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
                                mViewArrowDown.setRotation(0);
                                mViewArrowDown.setEnabled(true);
                            }
                        });
            }

        });

    }

    private void initTabData() {
        InputStreamReader reader = null;
        List<SubTab> mActiveData = null;
        try {
            reader = new InputStreamReader(
                    AppContext.getInstance().getAssets().open("sub_tab_original.json")
                    , "UTF-8");
            mActiveData = AppOperator.getGson().<ArrayList<SubTab>>fromJson(reader,
                    new TypeToken<ArrayList<SubTab>>() {
                    }.getType());

            if (mActiveData != null && mActiveData.size() > 0) {
                tabs.clear();
                tabs.addAll(mActiveData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            StreamUtil.close(reader);
        }

//        File file = AppContext.getInstance().getFileStreamPath("sub_tab_active.json");
//        List<SubTab> mActiveData = null;
//        if (file.exists()) {
//            try {
//                reader = new FileReader(file);
//                mActiveData = AppOperator.getGson().fromJson(reader,
//                        new TypeToken<ArrayList<SubTab>>() {
//                        }.getType());
//
//                if (mActiveData != null && mActiveData.size() > 0) {
//                    tabs.clear();
//                    tabs.addAll(mActiveData);
//                } else {
//                    tabs.clear();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                StreamUtil.close(reader);
//            }
//        }
        try {
            if (tabs.size() > 0)
                for (SubTab tab : tabs) {
                    mLayoutTab.addTab(mLayoutTab.newTab().setText(tab.getName()));
                }
        } catch (Exception e) {
            TLog.e("Ex ::::::::", e.getMessage());
        }
    }

    @Override
    protected void initData() {
        rightIcon.setVisibility(View.VISIBLE);
        rightIcon.setImageResource(R.mipmap.icon_sousuo);
    }


    @OnClick(R.id.rightIcon)
    void rithtIcon() {
        startActivity(new Intent(mContext, SearchActivity.class));
    }

    @OnClick(R.id.iv_arrow_down)
    void onClickArrow() {
        if (mViewTabPicker.getVisibility() == View.VISIBLE) {
            mViewArrowDown.setEnabled(false);
            mViewArrowDown.animate()
                    .rotation(0)
                    .setDuration(380)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            mViewArrowDown.setRotation(0);
                            mViewArrowDown.setEnabled(true);
                        }
                    });
            mViewTabPicker.onTurnBack();
        } else {
            mViewArrowDown.setEnabled(false);
            mViewArrowDown.animate()
                    .rotation(180)
                    .setDuration(380)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            mViewArrowDown.setRotation(180);
                            mViewArrowDown.setEnabled(true);
                        }
                    }).start();
            mViewTabPicker.show(mLayoutTab.getSelectedTabPosition());
        }
    }
}
