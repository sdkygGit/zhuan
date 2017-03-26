package com.zhuanworld.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.MissionDetailItem;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22 0022.
 */
public class MissionDetailAdapter extends RecyclerView.Adapter<VH> {

    List<MissionDetailItem> list;
    RequestManager requestManager;
    Context context;

    public MissionDetailAdapter(Context context, List<MissionDetailItem> list, RequestManager requestManager) {
        this.context = context;
        this.list = list;
        this.requestManager = requestManager;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mission_detail, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        requestManager.load(list.get(position).url).error(R.mipmap.lunboguanggao_test).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class VH extends RecyclerView.ViewHolder {
    public ImageView img;

    public VH(View itemView) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.img);
    }
}
