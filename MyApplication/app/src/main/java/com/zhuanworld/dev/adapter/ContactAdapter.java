package com.zhuanworld.dev.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.bean.ContactOrdinary;
import com.zhuanworld.dev.bean.ReadCommission;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */

public class ContactAdapter  extends RecyclerView.Adapter<ContactDataViewHolder> {

    List<ContactOrdinary> mList;
    Context mContactContext;

    public ContactAdapter(Context context, List<ContactOrdinary> list) {
        mContactContext = context;
        mList = list;
    }

    @Override
    public ContactDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContactContext).inflate(R.layout.item_contact, null);
        return new ContactDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactDataViewHolder holder, int position) {
        holder.mName.setText(mList.get(position).mContactName);
        holder.mPhone.setText(mList.get(position).mContactPhone);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
class ContactDataViewHolder extends RecyclerView.ViewHolder {
    public TextView mName;
    public TextView mPhone;

    public ContactDataViewHolder(View itemView) {
        super(itemView);
        mName = (TextView) itemView.findViewById(R.id.contact_name);
        mPhone = (TextView) itemView.findViewById(R.id.contact_phone);
    }
}
