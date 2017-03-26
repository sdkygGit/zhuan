package com.zhuanworld.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.Article;
import com.zhuanworld.dev.view.SubHeaderView;
import com.zhuanworld.dev.viewholder.SubItemViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public class SubFilpItemAdapter extends BaseAdapter {
    List<Article> mItems;
    Context context;
    int mold = 0;
    private RequestManager mImgLoader;
    int state;

    public SubFilpItemAdapter(Context context, List<Article> list, int mold, RequestManager requestManager) {
        this.context = context;
        this.mItems = list;
        this.mold = mold;
        this.mImgLoader = requestManager;
    }

    public void addData(List<Article> lists, boolean isFooter) {
        if (isFooter) {
            mItems.addAll(lists);
        } else {
            mItems.addAll(0, lists);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mItems == null)
            return 0;
        return mItems.size();
    }

    @Override
    public Article getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.recycler_sub_item, null);
            viewHolder.itemTitleTextView = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.itemImageView = (ImageView) convertView.findViewById(R.id.item_img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.
                itemTitleTextView.setText(
                mItems.get(position).title);
        mImgLoader
                .load(mItems.get(position).imgPath)
                .asBitmap()
                .placeholder(R.drawable.test)
                .error(R.drawable.test)
                .into(viewHolder.itemImageView);
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
//        if (mold == FOOTER_LOAD && position == 0) {
//            return 1;
//        }
        return 100;
    }

    class ViewHolder {
        public TextView itemTitleTextView;
        public ImageView itemImageView;
    }

}
