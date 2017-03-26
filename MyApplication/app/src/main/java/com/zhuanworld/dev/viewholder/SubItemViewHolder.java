package com.zhuanworld.dev.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhuanworld.dev.R;

/**
 * Created by Administrator on 2017/3/16 0016.
 */
public class SubItemViewHolder extends RecyclerView.ViewHolder {
    public TextView footerTextView;
    public ProgressBar pb;
    public TextView itemTitleTextView;
    public ImageView itemImageView;

    public SubItemViewHolder(View itemView, int viewType) {
        super(itemView);
        if (viewType == 1) {
            footerTextView = (TextView) itemView.findViewById(R.id.tv_footer);
            pb = (ProgressBar) itemView.findViewById(R.id.pb_footer);
        } else if (viewType == -1) {

        } else {
            itemTitleTextView = (TextView) itemView.findViewById(R.id.item_title);
            itemImageView = (ImageView) itemView.findViewById(R.id.item_img);
        }
    }

}
