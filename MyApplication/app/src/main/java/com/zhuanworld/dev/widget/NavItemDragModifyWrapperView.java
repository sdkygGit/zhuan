package com.zhuanworld.dev.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.adapter.NavItemDragAdapter;
import com.zhuanworld.dev.app.AppOperator;
import com.zhuanworld.dev.bean.SubTab;
import com.zhuanworld.dev.helper.OnRecyclerItemClickListener;
import com.zhuanworld.dev.media.SpaceGridItemDecoration;
import com.zhuanworld.dev.utils.StreamUtil;
import com.zhuanworld.dev.utils.TDevice;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class NavItemDragModifyWrapperView extends RecyclerView {

    //加载的navItem列表
    List<SubTab> mActiveData;

    NavItemDragAdapter mAdapter = null;

    boolean is_drag;

    ItemTouchHelper mItemTouchHelper;

    Paint mPaint;

    public void setIs_drag(boolean is_drag) {
        this.is_drag = is_drag;
    }

    public NavItemDragModifyWrapperView(Context context) {
        this(context, null);
    }

    public NavItemDragModifyWrapperView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavItemDragModifyWrapperView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NavItemDragModifyWrapperViewStyle);
        is_drag = a.getBoolean(R.styleable.NavItemDragModifyWrapperViewStyle_is_drag, false);

        a.recycle();
        init();
    }

    private void init() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        this.setLayoutManager(layoutManager);
        this.setOverScrollMode(View.OVER_SCROLL_NEVER);
        this.addItemDecoration(new SpacesItemDecoration(8));
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setAntiAlias(true);
        if (is_drag) {
            mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

                @Override
                public boolean isLongPressDragEnabled() {
                    return is_drag;
                }

                @Override
                public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
                    final int dragFlags;
                    final int swiperFlags;
                    if (recyclerView.getLayoutManager() instanceof
                            GridLayoutManager) {
                        dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                        swiperFlags = 0;
                    } else {
                        dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                        swiperFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    }

                    return makeMovementFlags(dragFlags, swiperFlags);
                }

                @Override
                public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder target) {
                    int fromPosition = viewHolder.getAdapterPosition();
                    int toPosition = target.getAdapterPosition();
                    SubTab subTab = mActiveData.remove(fromPosition);
                    mActiveData.add(toPosition, subTab);
                    mAdapter.notifyItemMoved(fromPosition, toPosition);
                    return false;
                }

                @Override
                public void onSwiped(ViewHolder viewHolder, int direction) {
//                    int position = viewHolder.getAdapterPosition();
//                    mAdapter.notifyItemRemoved(position);
//                    mActiveData.remove(position);
                }

                //当长按的时候调用
//                @Override
//                public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
//                    if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
//                        RecyclerView.ViewHolder vh = viewHolder;
//                        ((TextView) vh.itemView).setText("");
//                        mAdapter.notifyItemInserted(viewHolder.getAdapterPosition());
//                    }
//                    super.onSelectedChanged(viewHolder, actionState);
//                }

                //当手指松开的时候调用
                @Override
                public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                    super.clearView(recyclerView, viewHolder);
                    if (click != null) {
                        click.onDataChange();
                    }
                }
            });
            mItemTouchHelper.attachToRecyclerView(this);
        }

        addOnItemTouchListener(new OnRecyclerItemClickListener(this) {
            @Override
            public void onItemClick(ViewHolder vh) {
                if (click != null) {
                    click.itemClick(vh);
                }
            }
        });
    }


    public void setItemClickListener(NavItemDragAdapter.OnNavItemClickListener l) {
        if (mAdapter != null)
            mAdapter.setOnNavItemClickListener(l);
    }

    OnItemClick click;

    public void setOnItemClick(OnItemClick itemClickl) {
        this.click = itemClickl;
    }

    public interface OnItemClick {
        void itemClick(ViewHolder holder);

        void onDataChange();
    }

    public List<SubTab> getActiveData() {
        return mActiveData;
    }

    public void setData(List<SubTab> list) {
        if (mActiveData == null)
            this.mActiveData = list;
        else {
            mActiveData.clear();
            mActiveData.addAll(list);
        }
        if (mActiveData != null && mActiveData.size() > 0)
            this.setAdapter(mAdapter = new NavItemDragAdapter(getContext(), mActiveData));
    }

    public SubTab getItemSubTab(int pos) {
        if (pos > 0 && pos < mActiveData.size()) {

            return mActiveData.get(pos);
        }
        return null;
    }

    public void addItem(SubTab subTab) {
        if (mActiveData == null) {
            mActiveData = new ArrayList<>();
        }
        mActiveData.add(subTab);
        if (this.getAdapter() == null)
            this.setAdapter(mAdapter = new NavItemDragAdapter(getContext(), mActiveData));
        else
            this.getAdapter().notifyItemInserted(mActiveData.size());
    }

    public void removeItem(ViewHolder subTab) {
        mAdapter.notifyItemRemoved(subTab.getAdapterPosition());
        mActiveData.remove(subTab.itemView.getTag());
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            outRect.top = space;
        }
    }
}
