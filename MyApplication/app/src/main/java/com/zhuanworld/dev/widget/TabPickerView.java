package com.zhuanworld.dev.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.BuildConfig;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.SubTab;
import com.zhuanworld.dev.fragment.DynamicTabFragment;
import com.zhuanworld.dev.utils.TDevice;
import com.zhuanworld.dev.utils.TLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class TabPickerView extends FrameLayout {
    private RecyclerView mRecyclerActive;
    private RelativeLayout mLayoutTop;
    private NestedScrollView mViewScroller;

    private TabAdapter<TabAdapter.ViewHolder> mActiveAdapter;

    private int mSelectedIndex = 0;


    public TabPickerView(Context context) {
        this(context, null);
    }

    public TabPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TabPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWidgets();
    }

    private void initWidgets() {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.view_tab_picker, this, false);

        view.findViewById(R.id.emptyView).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getVisibility() == VISIBLE){
                    if (listener != null) {
                        onTurnBack();
                        listener.onHide();
                    }
                }

            }
        });

        mRecyclerActive = (RecyclerView) view.findViewById(R.id.view_recycler_active);
        mViewScroller = (NestedScrollView) view.findViewById(R.id.view_scroller);
        mLayoutTop = (RelativeLayout) view.findViewById(R.id.layout_top);

        addView(view);
    }

    public void show(int selectedIndex) {
        final int tempIndex = mSelectedIndex;
        mSelectedIndex = selectedIndex;
        mActiveAdapter.notifyItemChanged(tempIndex);
        mActiveAdapter.notifyItemChanged(mSelectedIndex);

        setVisibility(VISIBLE);
        mLayoutTop.setAlpha(0);
        mLayoutTop.animate().alpha(1).setDuration(380).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mLayoutTop.setAlpha(1);
            }
        });

        mViewScroller.setTranslationY(-mViewScroller.getHeight());
        mViewScroller.animate().translationY(0).setDuration(380).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mViewScroller.setTranslationY(0);
            }
        });
    }

    public void hide() {
        mLayoutTop.animate().alpha(0).setDuration(380).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setVisibility(GONE);
            }
        });
        mViewScroller.animate().translationY(-mViewScroller.getHeight()).setDuration(380);
    }

    public void initData(List<SubTab> subTabs) {

        mActiveAdapter = new TabAdapter<TabAdapter.ViewHolder>(subTabs) {
            @Override
            public TabAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new TabAdapter.ViewHolder(LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.view_tab_item, parent, false));
            }

            @Override
            public void onBindViewHolder(TabAdapter.ViewHolder holder, int position) {
                SubTab item = items.get(position);
                holder.mViewTab.setText(item.getName());
            }

            @Override
            public int getItemCount() {
                return items.size();
            }
        };

        mRecyclerActive.setAdapter(mActiveAdapter);
        mRecyclerActive.addItemDecoration(new SpacesItemDecoration(8));
        mRecyclerActive.setLayoutManager(new GridLayoutManager(getContext(), 4));
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = space;
        }
    }

    public boolean onTurnBack() {
        if (getVisibility() == VISIBLE) {
            hide();
            return true;
        }
        return false;
    }

    /**
     * Class TabAdapter
     *
     * @param <VH>
     */
    private abstract class TabAdapter<VH extends RecyclerView.ViewHolder>
            extends RecyclerView.Adapter<VH> {

        private boolean isEditMode = false;
        List<SubTab> items;

        TabAdapter(List<SubTab> items) {
            this.items = items;
        }

        void cancelEditMode() {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mRecyclerActive.getLayoutParams();
            params.height = params.WRAP_CONTENT;
            mRecyclerActive.setLayoutParams(params);

            isEditMode = false;
            notifyDataSetChanged();
        }

        boolean isEditMode() {
            return isEditMode;
        }

        /**
         * Tab View Holder
         */
        class ViewHolder extends RecyclerView.ViewHolder {
            TextView mViewTab;

            ViewHolder(View view) {
                super(view);
                mViewTab = (TextView) view.findViewById(R.id.tv_content);
                mViewTab.setTag(this);
                mViewTab.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onItemClick(ViewHolder.this.getAdapterPosition());
                            onTurnBack();
                        }
                    }
                });

            }
        }
    }

    OnTabItemListener listener;

    public void setOnTabItemListener(OnTabItemListener listener) {
        this.listener = listener;
    }

    public interface OnTabItemListener {
        void onItemClick(int pos);
        void onHide();
    }


}
