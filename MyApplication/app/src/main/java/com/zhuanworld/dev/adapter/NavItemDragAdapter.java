package com.zhuanworld.dev.adapter;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.SubTab;

import java.util.List;

/**
 * Created by Administrator on 2017/3/15 0015.
 */
public class NavItemDragAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    Context context;
    List<SubTab> list;

    OnNavItemClickListener mOnNavItemClickListener;

    public void setOnNavItemClickListener(OnNavItemClickListener listener) {
        this.mOnNavItemClickListener = listener;
    }

    public NavItemDragAdapter(Context context, List<SubTab> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        SubTab subTab = list.get(position);
        if (subTab.isFixed())
            return 1;
        else
            return 0;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.nav_item, null);
//        if (viewType == 0)
//            view.setBackgroundResource(R.drawable.nav_item_selector);
//        else
//            view.setBackgroundResource(R.drawable.xuxian_text_bg);
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.textView.setTag(list.get(position));
        holder.textView.setText(list.get(position).getName());
//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if (holder.getAdapterPosition() > 0 && holder.getAdapterPosition() < getItemCount()) {
//                if (mOnNavItemClickListener != null) {
//                    mOnNavItemClickListener.onNavItemClick(holder.itemView, holder.getAdapterPosition(), (SubTab) holder.itemView.getTag(),holder);
//                }
////                } else {
//////                    Toast.makeText(context, "count :" + holder.getAdapterPosition() + "name :" + list.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
////                }
//            }
//        });
    }

    public interface OnNavItemClickListener {
        void onNavItemClick(View view, int position, SubTab subTab,RecyclerView.ViewHolder holder);
    }
}

class ItemViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView;

    }
}
