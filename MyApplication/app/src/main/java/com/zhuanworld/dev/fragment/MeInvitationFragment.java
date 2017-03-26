package com.zhuanworld.dev.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.InvitationDialogActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的邀请
 * Created by Administrator on 2017/3/21 0021.
 */
public class MeInvitationFragment extends BaseFragment {

    @Bind(R.id.btn_wrapper)
    View btn_wrapper;

    @Bind(R.id.recycleView)
    RecyclerView recycleView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment__me_invitation;
    }

    @OnClick(R.id.invitationBtn)
    void invitationBtn() {
        startActivity(new Intent(mContext, InvitationDialogActivity.class));
    }

    @Override
    protected void initData() {


    }
}
