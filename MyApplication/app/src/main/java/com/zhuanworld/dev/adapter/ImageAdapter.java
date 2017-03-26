package com.zhuanworld.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.Image;
import com.zhuanworld.dev.config.ImageLoaderListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/3/11 0011.
 */
public class ImageAdapter extends RecyclerView.Adapter {
    private ImageLoaderListener loader;
    protected List<Image> mItems;
    protected LayoutInflater mInflater;
    OnItemClickListener onItemClickListener;

    public ImageAdapter(Context context, ImageLoaderListener loader) {
        this.loader = loader;
        mInflater = LayoutInflater.from(context);
        mItems = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        Image image = getItem(position);
        if (image.getId() == 0)
            return 0;
        return 1;
    }

    public final void clear() {
        if (mItems != null)
            this.mItems.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Image> items) {
        if (items != null) {
            this.mItems.addAll(items);
            notifyItemRangeInserted(this.mItems.size(), items.size());
        }
    }

    public void updateAll(List<Image> items) {
        if (items != null && items.size() > 0) {
            this.mItems.clear();
            this.mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    public final void addItem(Image item) {
        if (item != null) {
            this.mItems.add(item);
            notifyItemChanged(mItems.size());
        }
    }

    public Image getItem(int position) {
        if (position < 0 || position >= mItems.size())
            return null;
        return mItems.get(position);
    }

    public void switchSelectItem(int position) {
        Image img = mItems.get(position);
        if (img.isSelect()) {
            img.setSelect(false);
            mItems.remove(position);
            mItems.add(position, img);
        } else {
            for (int i = 0; i < mItems.size(); i++) {
                Image image = mItems.get(i);
                if (image.isSelect()) {
                    image.setSelect(false);
                    mItems.remove(i);
                    mItems.add(i, image);
                    notifyItemChanged(i);
                    break;
                }
            }
            mItems.remove(position);
            img.setSelect(true);
            mItems.add(position, img);
        }
        notifyItemChanged(position);
    }

    public void updateItem(int position) {
        if (getItemCount() > position) {
            notifyItemChanged(position);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder holder;
        if (viewType == 0)
            holder = new CamViewHolder(mInflater.inflate(R.layout.item_list_cam, parent, false));
        else
            holder = new ImageViewHolder(mInflater.inflate(R.layout.item_list_image, parent, false));
        if (holder != null) {
            holder.itemView.setTag(holder);
//            holder.itemView.setOnLongClickListener(onLongClickListener);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.onItemClick(holder.getAdapterPosition(), holder.getItemId());
                }
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Image item = getItem(position);
        if (item.getId() != 0) {
            ImageViewHolder h = (ImageViewHolder) holder;
            h.mCheckView.setSelected(item.isSelect());
            h.mMaskView.setVisibility(item.isSelect() ? View.VISIBLE : View.GONE);

            // Show gif mask
            h.mGifMask.setVisibility(item.getPath().toLowerCase().endsWith("gif") ?
                    View.VISIBLE : View.GONE);

            loader.displayImage(h.mImageView, item.getPath());
        }
    }

    @Override
    public int getItemCount() {

        if (mItems == null)
            return 0;
        return mItems.size();
    }

    private static class CamViewHolder extends RecyclerView.ViewHolder {
        CamViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        ImageView mCheckView;
        ImageView mGifMask;
        View mMaskView;

        ImageViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_image);
            mCheckView = (ImageView) itemView.findViewById(R.id.cb_selected);
            mMaskView = itemView.findViewById(R.id.lay_mask);
            mGifMask = (ImageView) itemView.findViewById(R.id.iv_is_gif);
        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, long itemId);
    }

}
