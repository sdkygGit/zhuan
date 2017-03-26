package com.zhuanworld.dev.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.zhuanworld.dev.activity.SelectImageActivity;
import com.zhuanworld.dev.adapter.TweetSelectImageAdapter;
import com.zhuanworld.dev.bean.SelectOptions;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
public class TweetPicturesPreviewer extends RecyclerView implements TweetSelectImageAdapter.Callback {
    private TweetSelectImageAdapter mImageAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private RequestManager mCurImageLoader;

    public TweetPicturesPreviewer(Context context) {
        this(context, null);
    }

    public TweetPicturesPreviewer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public TweetPicturesPreviewer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mImageAdapter = new TweetSelectImageAdapter(this);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        this.setLayoutManager(layoutManager);
        this.setAdapter(mImageAdapter);
        this.setOverScrollMode(View.OVER_SCROLL_NEVER);

        ItemTouchHelper.Callback callback = new TweetPicturesPreviewerItemTouchCallback(mImageAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(this);
    }

    public void set(String[] paths) {
        mImageAdapter.clear();
        for (String path : paths) {
            mImageAdapter.add(path);
        }
        mImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreClick() {
        SelectImageActivity.show(getContext(), new SelectOptions.Builder()
                .setHasCam(true)
                .setSelectCount(9)
                .setSelectedImages(mImageAdapter.getPaths())
                .setCallback(new SelectOptions.Callback() {
                    @Override
                    public void doSelected(String[] images) {
                        set(images);
                    }
                }).build());
    }

    @Override
    public RequestManager getImgLoader() {
        if (mCurImageLoader == null) {
            mCurImageLoader = Glide.with(getContext());
        }
        return mCurImageLoader;
    }

    @Override
    public void onStartDrag(ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    public String[] getPaths() {
        return mImageAdapter.getPaths();
    }
}
