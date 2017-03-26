package com.zhuanworld.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.IncomeDetails;


import java.util.List;

/**
 * Created by Administrator on 2017/3/25.
 */

public class IncomeDetailsAdapter extends RecyclerView.Adapter<IncomeViewHolder> {

    List<IncomeDetails> mList;
    Context mContactContext;

    public IncomeDetailsAdapter(Context context, List<IncomeDetails> list) {
        mContactContext = context;
        mList = list;
    }

    @Override
    public IncomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContactContext).inflate(R.layout.item_income_details, null);
        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IncomeViewHolder holder, int position) {
        holder.mDate.setText(mList.get(position).mDate);
        holder.mIncome.setText(mList.get(position).mIncome);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
class IncomeViewHolder extends RecyclerView.ViewHolder {
    public TextView mDate;
    public TextView mIncome;

    public IncomeViewHolder(View itemView) {
        super(itemView);
        mDate = (TextView) itemView.findViewById(R.id.date_item_income_details);
        mIncome = (TextView) itemView.findViewById(R.id.income_item_income_details);
    }
}
