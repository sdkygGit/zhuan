package com.zhuanworld.dev.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class MissionCenterFragment extends BaseTitleFragment {

    @Bind(R.id.title_tv)
    TextView tv_title;

    @Bind(R.id.rightIcon)
    ImageView rightIcon;

    @Bind(R.id.viewPager)
    ViewPager viewPager;

    @Bind(R.id.tab_one)
    TextView tabOne;

    @Bind(R.id.tab_two)
    TextView tabTwo;

    private FragmentPagerAdapter mAdapter;

    private Fragment mCurFragment;

    String[] tabs = {"百度CPC", "积分墙"};

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mission_center;
    }

    @Override
    protected void initWidget(View root) {
        mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return BaiduCpcFragment.newInstance();
                }
                return FragmentIntegral.newInstance();
            }

            @Override
            public int getCount() {
                return tabs.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabs[position];
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
        };
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tabOne.setSelected(true);
                    tabTwo.setSelected(false);
                } else {
                    tabOne.setSelected(false);
                    tabTwo.setSelected(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        viewPager.setAdapter(mAdapter);
        tabOne.setSelected(true);
    }

    @OnClick(R.id.tab_two_wrap)
    void tab_two() {
        int index = viewPager.getCurrentItem();
        if (index != 1) {
            viewPager.setCurrentItem(1);
        }
    }

    @OnClick(R.id.tab_one_wrap)
    void tab_one() {
        int index = viewPager.getCurrentItem();
        if (index != 0) {
            viewPager.setCurrentItem(0);
        }
    }

    @Override
    protected void initData() {
        tv_title.setText("任务中心");
    }
}
