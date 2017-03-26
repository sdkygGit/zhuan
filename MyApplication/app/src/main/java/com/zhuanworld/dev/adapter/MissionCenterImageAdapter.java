package com.zhuanworld.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.BaiduCpc;

import java.util.List;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class MissionCenterImageAdapter extends RecyclerView.Adapter<ViewHolder> {
    List<BaiduCpc> list;
    Context context;
    RequestManager requestManager;
    ItemClickListener listener;

    public MissionCenterImageAdapter(List<BaiduCpc> list, Context context, RequestManager requestManager) {
        this.list = list;
        this.context = context;
        this.requestManager = requestManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_image_title, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.textView.setText(list.get(position).title);
//        requestManager.load(list.get(position).imgUrl).error(R.mipmap.ic_split_graph).into(holder.imageView);
        requestManager.load(list.get(position).imgUrl).error(R.drawable.test).into(holder.imageView);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(int pos);
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;
    public View root;

    public ViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.iv_image);
        textView = (TextView) itemView.findViewById(R.id.textTitle);
        root = itemView.findViewById(R.id.root);
    }
}
