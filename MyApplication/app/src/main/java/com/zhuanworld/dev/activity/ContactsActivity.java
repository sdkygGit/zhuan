package com.zhuanworld.dev.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.adapter.ViewPagerAdapter;
import com.zhuanworld.dev.fragment.BaseFragment;
import com.zhuanworld.dev.fragment.ContactGoldFragment;
import com.zhuanworld.dev.fragment.ContactOrdinaryFragment;
import com.zhuanworld.dev.fragment.PlatinumFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/18 0018.
 * 我的人脉
 */
public class ContactsActivity extends BaseActivity {
    private ViewPagerAdapter mAdapter;
    private List<BaseFragment> mFragments = new ArrayList<>();
    private ContactOrdinaryFragment mContactOrdinaryFragment;
    private ContactGoldFragment mContactGoldFragment;
    private PlatinumFragment mPlatinumFragment;

    @Bind(R.id.title_login_forget_leftImg)
    ImageView mContactTitleLeft;

    @Bind(R.id.title_login_forget_center)
    TextView mContactTitleCenter;

    @Bind(R.id.contacts_viewPager)
    ViewPager mContactsVp;

    @Bind(R.id.contacts_radioGroup)
    RadioGroup mRadioGroup;

    @Bind(R.id.contacts_ordinary)
    RadioButton mContactsOrdinary;
    @Bind(R.id.contacts_gold)
    RadioButton mContactsGold;
    @Bind(R.id.contacts_platinum)
    RadioButton mContactsPlatinum;

    @Override
    protected void initWidget() {
        mContactsVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mRadioGroup.clearCheck();
                switch (position) {
                    case 0:
                        initTextColor();
                        mRadioGroup.check(R.id.contacts_ordinary);
                        mContactsOrdinary.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case 1:
                        initTextColor();
                        mRadioGroup.check(R.id.contacts_gold);
                        mContactsGold.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case 2:
                        initTextColor();
                        mRadioGroup.check(R.id.contacts_platinum);
                        mContactsPlatinum.setTextColor(getResources().getColor(R.color.white));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @OnClick(R.id.contacts_ordinary)
    void contacts_ordinary() {
        initTextColor();
        mContactsOrdinary.setTextColor(getResources().getColor(R.color.white));
        mContactsVp.setCurrentItem(0);
    }

    @OnClick(R.id.contacts_gold)
    void contacts_gold() {
        initTextColor();
        mContactsGold.setTextColor(getResources().getColor(R.color.white));
        mContactsVp.setCurrentItem(1);
    }

    @OnClick(R.id.contacts_platinum)
    void contacts_platinum() {
        initTextColor();
        mContactsPlatinum.setTextColor(getResources().getColor(R.color.white));
        mContactsVp.setCurrentItem(2);
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_contacts;
    }

    @Override
    protected void initData() {
        super.initData();
        initTitle();
        initVP();
    }

    /**
     * 对ViewPager进行初始化设置
     */
    private void initVP() {
        initFragmentList();
        FragmentManager manager = getSupportFragmentManager();
        mAdapter = new ViewPagerAdapter(manager, mFragments);
        if (mContactsVp != null) {
            mContactsVp.setAdapter(mAdapter);
        }
    }

    /**
     * 对viewPager中将用到的Fagment集合就行初始化
     */
    private void initFragmentList() {
        FragmentManager manager = getSupportFragmentManager();
        mPlatinumFragment = new PlatinumFragment();
        mContactGoldFragment = new ContactGoldFragment();
        mContactOrdinaryFragment = new ContactOrdinaryFragment();
        mFragments.add(mContactOrdinaryFragment);
        mFragments.add(mContactGoldFragment);
        mFragments.add(mPlatinumFragment);
    }

    void initTitle() {
        mContactTitleLeft.setImageResource(R.mipmap.btn_return_white);
        mContactTitleCenter.setText("我的人脉");
    }

    void initTextColor() {
        mContactsOrdinary.setTextColor(getResources().getColor(R.color.black));
        mContactsGold.setTextColor(getResources().getColor(R.color.black));
        mContactsPlatinum.setTextColor(getResources().getColor(R.color.black));

    }

}
