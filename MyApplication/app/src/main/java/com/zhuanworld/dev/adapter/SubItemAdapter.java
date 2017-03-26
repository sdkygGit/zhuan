package com.zhuanworld.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.Article;
import com.zhuanworld.dev.bean.SubTab;
import com.zhuanworld.dev.view.SubHeaderView;
import com.zhuanworld.dev.viewholder.SubItemViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public class SubItemAdapter extends RecyclerView.Adapter<SubItemViewHolder> {
    public static final int FOOTER_LOAD = 0x110;
    public static final int BOTH_LOAD = 0x115;
    public static final int NO_FOOTER_LOAD = 0x12;
    List<Article> mItems;
    Context context;
    int mold = 0;
    private RequestManager mImgLoader;
    SubHeaderView mHeadView;

    int state;

    public SubItemAdapter(Context context, List<Article> list, int mold, RequestManager requestManager) {
        this.context = context;
        this.mItems = list;
        this.mold = mold;
        this.mImgLoader = requestManager;
    }

    public void setHeadView(SubHeaderView view) {
        mHeadView = view;
    }

    @Override
    public SubItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.recycler_footer_view, null);
        } else if (viewType == -1) {
            view = mHeadView;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.recycler_sub_item, null);
        }
        return new SubItemViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(SubItemViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
//            TextView tv = (TextView) holder.itemView.findViewById(R.id.tv_footer);
            if (state == 1) {
                holder.footerTextView.setText("已全部加载");
                holder.pb.setVisibility(View.GONE);
            } else {
                holder.footerTextView.setText("数据加载中...");
                holder.pb.setVisibility(View.VISIBLE);
            }

        } else if (holder.getItemViewType() == -1) {

        } else if (holder.getItemViewType() == 100) {
            holder.itemTitleTextView.setText(mItems.get(position).title);
            mImgLoader
                    .load(mItems.get(position).imgPath)
                    .asBitmap()
                    .placeholder(R.drawable.test)
                    .error(R.drawable.test)
                    .into(holder.itemImageView);

        }
    }

    @Override
    public int getItemCount() {
        if (mItems == null)
            return 0;
        else if ((mold == FOOTER_LOAD) && mItems.size() > 0)
            return mItems.size() + 1;
        else if (mold == BOTH_LOAD && mItems.size() > 0)
            return mItems.size() + 1;
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mold == FOOTER_LOAD) {
            if (position == mItems.size()) {
                return 1;
            }
        } else if (mold == BOTH_LOAD) {
            if (position == mItems.size()) {
                return 1;
            } else if (position == 0 && mHeadView != null) {
                return -1;
            }
        }
        return 100;
    }

    public void switchState(int state) {
        this.state = state;
        notifyItemChanged(getItemCount() - 1);
    }
}
