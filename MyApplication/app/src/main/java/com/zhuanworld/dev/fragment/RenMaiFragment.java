package com.zhuanworld.dev.fragment;

import android.content.Intent;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.InvitationDialogActivity;

import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class RenMaiFragment extends BaseFragment {

    @OnClick(R.id.invitationBtn)
    void invitation(){
        startActivity(new Intent(mContext, InvitationDialogActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_renmai;
    }
}
