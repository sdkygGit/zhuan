package com.zhuanworld.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.ReadCommission;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22 0022.
 * 阅读提成adapter
 */
public class ReadCommissionDataAdapter extends RecyclerView.Adapter<ReadCommissionDataViewHolder> {

    List<ReadCommission> list;
    Context context;

    public ReadCommissionDataAdapter(Context context, List<ReadCommission> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ReadCommissionDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_read_commission, null);
        return new ReadCommissionDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReadCommissionDataViewHolder holder, int position) {
        holder.jinE.setText(list.get(position).AmountMoney);
        holder.date.setText(list.get(position).date);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class ReadCommissionDataViewHolder extends RecyclerView.ViewHolder {
    public TextView jinE;
    public TextView date;

    public ReadCommissionDataViewHolder(View itemView) {
        super(itemView);
        jinE = (TextView) itemView.findViewById(R.id.jinE);
        date = (TextView) itemView.findViewById(R.id.date);
    }
}
