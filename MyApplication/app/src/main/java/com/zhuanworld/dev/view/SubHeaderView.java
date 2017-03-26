package com.zhuanworld.dev.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.RequestManager;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.SubPager;
import com.zhuanworld.dev.widget.CirclePagerIndicator;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public class SubHeaderView extends RelativeLayout implements Runnable, ViewPager.OnPageChangeListener {
    List<SubPager> banners;
    ViewPager viewPager;
    protected RequestManager mImageLoader;
    protected Handler mHandler;
    protected BannerAdapter mAdapter;
    protected int mCurrentItem;
    private boolean isScrolling;
    protected CirclePagerIndicator mIndicator;

    public SubHeaderView(Context context, RequestManager mImageLoader, List<SubPager> banners) {
        super(context);
        this.banners = banners;
        this.mImageLoader = mImageLoader;
        init(context);
    }

    private void init(Context context) {
        if (banners.size() > 0) {
            if (mHandler == null) {
                mHandler = new Handler();
            }
            mHandler.postDelayed(this, 5000);
        }
        LayoutInflater.from(context).inflate(R.layout.sub_header_view, this, true);
        viewPager = (ViewPager) findViewById(R.id.vp_banner);
        mAdapter = new BannerAdapter();
        viewPager.setAdapter(mAdapter);
        viewPager.addOnPageChangeListener(this);
        mIndicator = (CirclePagerIndicator) findViewById(R.id.indicator);
        mIndicator.bindViewPager(viewPager);
        mIndicator.setCount(banners.size());

        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isScrolling = true;
                    case MotionEvent.ACTION_UP:
                        isScrolling = false;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        isScrolling = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        isScrolling = true;
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void run() {
        runTurn();
    }

    void runTurn() {
        mHandler.postDelayed(this, 1000);
        if (viewPager.getChildCount() <= 0) {
            return;
        }
        if (isScrolling)
            return;
        mCurrentItem++;
        viewPager.setCurrentItem(mCurrentItem);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        isScrolling = mCurrentItem != position;
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentItem = position;
        isScrolling = false;
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        isScrolling = state != ViewPager.SCROLL_STATE_IDLE;
    }

    private class BannerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return banners.size() == 1 ? 1 : Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ViewEventBanner view = new ViewEventBanner(getContext());
            if (banners.size() != 0) {
                int p = position % banners.size();
                if (p >= 0 && p < banners.size()) {
                    view.initData(mImageLoader, banners.get(p));
                }
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object instanceof ViewEventBanner) {
                container.removeView((ViewEventBanner) object);
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mHandler == null)
            mHandler = new Handler();
        mHandler.postDelayed(this, 5000);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mHandler == null)
            return;
        mHandler.removeCallbacks(this);
        mHandler = null;
    }
}
