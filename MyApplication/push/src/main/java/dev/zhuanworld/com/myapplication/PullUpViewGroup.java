package dev.zhuanworld.com.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class PullUpViewGroup extends FrameLayout {

    ListView listView;
    RecyclerView recyclerView;

    int downY;
    int lastY;
    boolean scrollTop;
    boolean scrollBottom;

    //触动滑动事件距离界点
    private int mTouchSlop;
    int startY = 0;

    public PullUpViewGroup(Context context) {
        this(context, null);
    }

    public PullUpViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullUpViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PullUpViewGroup);
        init();
    }

    private void init() {
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        scrollTop = true;
        scrollBottom = false;
    }

    @Override
    protected void onLayout(final boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (getChildCount() > 0) {
            if (listView == null) {
                listView = (ListView) getChildAt(0);
                listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                        if (firstVisibleItem == 0) {
                            View firstVisibleItemView = listView.getChildAt(0);
                            if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
                                scrollTop = true;
                            } else scrollTop = false;
                        } else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                            View lastVisibleItemView = listView.getChildAt(listView.getChildCount() - 1);
                            if (lastVisibleItemView != null && lastVisibleItemView.getBottom() == listView.getHeight()) {
                                scrollBottom = true;
                            } else scrollBottom = false;
                        }

                    }
                });
            }
        }
    }

    //是否下拉
    boolean isPullDown() {
        return (lastY - downY) > mTouchSlop;
    }

    //是否上拉
    boolean isPullUp() {
        return (downY - lastY) > mTouchSlop;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        boolean intercept = false;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) e.getRawY();
                downY = (int) e.getRawY();
                intercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                lastY = (int) e.getRawY();
                if (scrollTop) {
                    if (isPullDown()) {
                        intercept = true;
                    }
                } else if (scrollBottom) {
                    if (isPullUp()) {
                        intercept = true;
                    }
                } else {
                    intercept = false;
                }
                break;
        }
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int dey = (int) (event.getRawY() - startY);
                scrollTo(0, getScrollY() - dey);
                startY = (int) event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
//                scrollTo(0, 0);
                reset(getScrollY());
                break;
        }
        return true;
    }

    void reset(final int y) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                if (y >= 0) {
                    int y2 = y;
                    y2 /= 2;
                    if (y2 < 5) {
                        y2 = 0;
                    }
                    scrollTo(0, y2);
                    if (y2 != 0)
                        reset(y2);
                } else {
                    int y2 = y;
                    y2 /= 2;
                    if (y2 > -5) {
                        y2 = 0;
                    }
                    scrollTo(0, y2);
                    if (y2 != 0)
                        reset(y2);
                }
            }
        }, 60);
    }
}
