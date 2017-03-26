package com.zhuanworld.dev.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.zhuanworld.dev.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
public class BaseViewPagerFragment extends BaseFragment {

    protected TabLayout mTabNav;

    @Bind(R.id.base_viewPager)
    protected ViewPager mBaseViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_viewpager;
    }

    @Override
    protected void initWidget(View root) {
//        mTabNav.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabNav = (TabLayout) root.findViewById(R.id.layout_tab);

        BaseViewPagerAdapter adapter = new BaseViewPagerAdapter(getChildFragmentManager(), getPagers());
        mBaseViewPager.setAdapter(adapter);
        mTabNav.setupWithViewPager(mBaseViewPager);
        mTabNav.setSmoothScrollingEnabled(true);
    }

    protected PagerInfo[] getPagers() {

        Bundle bundle = new Bundle();
        bundle.putInt("sss", 0);
        return new PagerInfo[]{

                new PagerInfo("最新动弹", TTTFragment.class,
                        bundle),
                new PagerInfo("热门动弹", TTTFragment.class,
                        bundle),
                new PagerInfo("热门动弹", TTTFragment.class,
                        bundle),
                new PagerInfo("热门动弹", TTTFragment.class,
                        bundle),
                new PagerInfo("热门动弹", TTTFragment.class,
                        bundle),
                new PagerInfo("热门动弹", TTTFragment.class,
                        bundle),
                new PagerInfo("热门动弹", TTTFragment.class,
                        bundle),
                new PagerInfo("热门动弹", TTTFragment.class,
                        bundle),
                new PagerInfo("热门动弹", TTTFragment.class,
                        bundle)
        };
    }

    public static class PagerInfo {
        private String title;
        private Class<?> clx;
        private Bundle args;

        public PagerInfo(String title, Class<?> clx, Bundle args) {
            this.title = title;
            this.clx = clx;
            this.args = args;
        }
    }

    public class BaseViewPagerAdapter extends FragmentPagerAdapter {
        private PagerInfo[] mInfoList;
        private Fragment mCurFragment;

        public BaseViewPagerAdapter(FragmentManager fm, PagerInfo[] infoList) {
            super(fm);
            mInfoList = infoList;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            if (object instanceof Fragment) {
                mCurFragment = (Fragment) object;
            }
        }

        public Fragment getCurFragment() {
            return mCurFragment;
        }

        @Override
        public Fragment getItem(int position) {
            PagerInfo info = mInfoList[position];
            return Fragment.instantiate(getContext(), info.clx.getName(), info.args);
        }

        @Override
        public int getCount() {
            return mInfoList.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mInfoList[position].title;
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }
}
