package com.zhuanworld.dev.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.account.AccountHelper;
import com.zhuanworld.dev.activity.LoginActivity;
import com.zhuanworld.dev.bean.User;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/21 0021.
 */
public class InvitationFragment extends BaseTitleFragment {

    //内容管理
    FragmentManager fm;
    //缓存
    Fragment[] fragments = new Fragment[3];
    @Bind(R.id.renMai)
    TextView renmai;
    @Bind(R.id.meInvitation)
    TextView meInvitation;
    @Bind(R.id.readCommission)
    TextView readCommission;

    @Bind(R.id.title_tv)
    TextView title_tv;

    @Bind(R.id.rightIcon)
    ImageView rightIcon;

    @OnClick(R.id.renMai)
    void renMai() {
        if (fragments[0] == null) {
            RenMaiFragment renMaiFragment = new RenMaiFragment();
            fragments[0] = renMaiFragment;
        }
        fm.beginTransaction().replace(R.id.content, fragments[0]).commit();
        renmai.setSelected(true);
        meInvitation.setSelected(false);
        readCommission.setSelected(false);
    }

    @OnClick(R.id.meInvitation)
    void meInvitation() {
        if (fragments[1] == null) {
            MeInvitationFragment renMaiFragment = new MeInvitationFragment();
            fragments[1] = renMaiFragment;
        }
        fm.beginTransaction().replace(R.id.content, fragments[1]).commitNow();
        renmai.setSelected(false);
        meInvitation.setSelected(true);
        readCommission.setSelected(false);
    }

    @OnClick(R.id.readCommission)
    void readCommission() {
        if (fragments[2] == null) {
            ReadCommissionFragment renMaiFragment = new ReadCommissionFragment();
            fragments[2] = renMaiFragment;
        }
        fm.beginTransaction().replace(R.id.content, fragments[2]).commitNow();
        renmai.setSelected(false);
        meInvitation.setSelected(false);
        readCommission.setSelected(true);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_invitation;
    }

    @Override
    protected void initWidget(View root) {
    }

    @Override
    protected void initData() {
        fm = getChildFragmentManager();
        title_tv.setText("邀请");
        rightIcon.setVisibility(View.INVISIBLE);

        RenMaiFragment renMaiFragment = new RenMaiFragment();
        fragments[0] = renMaiFragment;
        fm.beginTransaction().replace(R.id.content, fragments[0]).commitNow();
        renmai.setSelected(true);
        meInvitation.setSelected(false);
        readCommission.setSelected(false);
    }
}
