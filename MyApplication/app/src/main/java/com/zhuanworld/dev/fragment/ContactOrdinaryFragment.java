package com.zhuanworld.dev.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.adapter.ContactAdapter;
import com.zhuanworld.dev.bean.ContactOrdinary;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * 普通会员
 */
public class ContactOrdinaryFragment extends BaseFragment {
    private ContactAdapter mAdapter;

    @Bind(R.id.contact_ordinary_recycleView)
    RecyclerView recyclerView;


    public ContactOrdinaryFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact_ordinary;
    }

    @Override
    protected void initData() {

        List<ContactOrdinary> contactList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ContactOrdinary contactOrdinary = new ContactOrdinary("name" + i, "1314016769" + i);
            contactList.add(contactOrdinary);

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        //添加自定义的分隔线
//        DividerLine divider = new DividerLine(mContext, DividerLine.VERTICAL_LIST);
//        divider.setDivider(R.drawable.divider);
//        recyclerView.addItemDecoration(divider);
        mAdapter = new ContactAdapter(getActivity(),contactList);
        recyclerView.setAdapter(mAdapter);

    }

}
