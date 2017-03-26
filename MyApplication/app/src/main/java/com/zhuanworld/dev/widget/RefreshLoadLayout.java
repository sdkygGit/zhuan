package com.zhuanworld.dev.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.zhuanworld.dev.R;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class RefreshLoadLayout extends SwipeRefreshLayout implements OnRefreshListener {


    boolean mCanLoadMore;
    private RecyclerView mRecycleView;
    private boolean mIsOnLoading;
    private boolean mHasMore = true;

    private int mYDown;
    private int mLastY;
    //触动滑动事件距离界点
    private int mTouchSlop;
    OnLoadDataListener mListener;

    public void setOnLoadDataListener(OnLoadDataListener listener) {
        this.mListener = listener;
    }

    public RefreshLoadLayout(Context context) {
        this(context, null);
    }

    public RefreshLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setOnRefreshListener(this);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RefreshLoadLayoutStyle);
        mCanLoadMore = a.getBoolean(R.styleable.RefreshLoadLayoutStyle_can_loadmore, false);
        a.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mRecycleView == null) {

            if (getChildCount() > 0) {
                View childView = getChildAt(0);
                if (!(childView instanceof RecyclerView)) {
                    return;
                }
                mRecycleView = (RecyclerView) childView;
                mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        if (canLoad() && mCanLoadMore) {
                            loadData();
                        }
                    }
                });
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mYDown = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                mLastY = (int) event.getRawY();
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public void loadData() {
        if (mListener != null) {
            setOnLoading(true);
            mListener.onLoadMore();
        }
    }

    public void onComplete() {
        setOnLoading(false);
        setRefreshing(false);
        mHasMore = true;
    }

    @Override
    public void onRefresh() {
        if (mListener != null && !mIsOnLoading)
            mListener.onRefresh();

    }

    boolean canLoad() {
        return isScrollBottom() && !mIsOnLoading && isPullUp() && mHasMore;
    }

    //是否向上滑动
    boolean isPullUp() {
        return (mYDown - mLastY) >= mTouchSlop;
    }

    /**
     * 设置正在加载
     *
     * @param loading 设置正在加载
     */
    public void setOnLoading(boolean loading) {
        mIsOnLoading = loading;
        if (!mIsOnLoading) {
            mYDown = 0;
            mLastY = 0;
        }
    }

    /**
     * 判断是否到了最底部
     */
    private boolean isScrollBottom() {
        return (mRecycleView != null && mRecycleView.getAdapter() != null)
                && getLastVisiblePosition() == (mRecycleView.getAdapter().getItemCount() - 1);
    }

    public int getLastVisiblePosition() {
        int position;
        if (mRecycleView.getLayoutManager() instanceof LinearLayoutManager) {
            position = ((LinearLayoutManager) mRecycleView.getLayoutManager()).findLastVisibleItemPosition();
        } else if (mRecycleView.getLayoutManager() instanceof GridLayoutManager) {
            position = ((GridLayoutManager) mRecycleView.getLayoutManager()).findLastVisibleItemPosition();
        } else if (mRecycleView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) mRecycleView.getLayoutManager();
            int[] lastPositions = layoutManager.findLastVisibleItemPositions(new int[layoutManager.getSpanCount()]);
            position = getMaxPosition(lastPositions);
        } else {
            position = mRecycleView.getLayoutManager().getItemCount() - 1;
        }
        return position;
    }

    /**
     * 获得最大的位置
     *
     * @param positions 获得最大的位置
     * @return 获得最大的位置
     */
    private int getMaxPosition(int[] positions) {
        int maxPosition = Integer.MIN_VALUE;
        for (int position : positions) {
            maxPosition = Math.max(maxPosition, position);
        }
        return maxPosition;
    }

    public interface OnLoadDataListener {
        void onRefresh();

        void onLoadMore();
    }
}
