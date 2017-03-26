package com.zhuanworld.dev.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.activity.AboutUsActivity;
import com.zhuanworld.dev.activity.ContactsActivity;
import com.zhuanworld.dev.activity.IncomeDetailsActivity;
import com.zhuanworld.dev.activity.MeSettingActivity;
import com.zhuanworld.dev.activity.MyAccountActivity;
import com.zhuanworld.dev.activity.PersonalDataActivity;
import com.zhuanworld.dev.activity.SelectImageActivity;
import com.zhuanworld.dev.activity.WithDrawsCashActivity;
import com.zhuanworld.dev.bean.SelectOptions;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/17 0017.
 */
public class MeFragment extends BaseFragment {

    @Bind(R.id.iv_icon)
    ImageView iv_icon;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initWidget(View root) {
        getImgLoader().load("").error(R.drawable.test).into(iv_icon);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick(R.id.personal_data)
    void personal_data() {
        startActivity(new Intent(getActivity(), PersonalDataActivity.class));
    }

    @OnClick(R.id.ren_mai)
    void ren_mai() {
        startActivity(new Intent(mContext, ContactsActivity.class));
    }

    @OnClick(R.id.ti_sheng_deng_ji)
    void ti_sheng_deng_ji() {
//       startActivity(new Intent(mContext, UpGradeActivity.class));
    }

    @OnClick(R.id.zhang_hu)
    void zhang_hu() {
        startActivity(new Intent(mContext, MyAccountActivity.class));
    }

    @OnClick(R.id.shou_ru_ming_xi)
    void shou_ru_ming_xi()
    {
        startActivity(new Intent(mContext, IncomeDetailsActivity.class));

    }

    @OnClick(R.id.dui_huan_ti_xian)
    void dui_huan_ti_xian()
    {
        startActivity(new Intent(mContext, WithDrawsCashActivity.class));

    }

    @OnClick(R.id.shou_ru_pai_hang)
    void shou_ru_pai_hang() {
//        startActivity(new Intent(mContext, IncomeRankingActivity.class));
    }


    @OnClick(R.id.guan_yu_wo_men)
    void guan_yu_wo_men() {
        startActivity(new Intent(mContext, AboutUsActivity.class));
    }

    @OnClick(R.id.me_setting)
    void me_setting() {
        startActivity(new Intent(mContext, MeSettingActivity.class));
    }

    @OnClick(R.id.iv_icon)
    void iv_icon() {
        SelectImageActivity.show(getContext(), new SelectOptions.Builder()
                .setHasCam(true)
                .setSelectCount(1)
                .setCallback(new SelectOptions.Callback() {
                    @Override
                    public void doSelected(String[] images) {
                        Toast.makeText(mContext, "选择了 :" + images[0], Toast.LENGTH_SHORT).show();
                    }
                }).build());
    }

    @OnClick(R.id.tui_chu_deng_lu)
    void tui_chu() {
        AppContext.showToast("退出登录");
    }
}
