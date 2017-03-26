package com.zhuanworld.dev.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuanworld.dev.AppConfig;
import com.zhuanworld.dev.AppContext;
import com.zhuanworld.dev.BaseApplication;
import com.zhuanworld.dev.R;
import com.zhuanworld.dev.account.AccountHelper;
import com.zhuanworld.dev.bean.User;
import com.zhuanworld.dev.fragment.BaseViewPagerFragment;
import com.zhuanworld.dev.fragment.DynamicTabFragment;
import com.zhuanworld.dev.fragment.InvitationFragment;
import com.zhuanworld.dev.fragment.MainFragment;
import com.zhuanworld.dev.fragment.MeFragment;
import com.zhuanworld.dev.fragment.MissionCenterFragment;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
public class MainActivity extends BaseActivity {

    private final int RC_EXTERNAL_STORAGE = 0x04;//存储权限
    //两次返回事件间隔
    private long mBackPressedTime;
    //tab热键容器
    private FragmentTabHost fragmentTabHost;
    //返回事件
    OnActivityResultListener mListener;

    private TabWidget mTabWidget;

    public void setOnActivityResultListener(OnActivityResultListener l) {
        this.mListener = l;
    }

    ImageView quick_option_iv;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doNewIntent(getIntent(), true);
    }

    private void doNewIntent(Intent intent, boolean b) {
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        doNewIntent(intent, false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onBackPressed() {
        boolean isDoubleClick = BaseApplication.get(AppConfig.KEY_DOUBLE_CLICK_EXIT, true);
        if (isDoubleClick) {
            long curTime = SystemClock.uptimeMillis();
            if ((curTime - mBackPressedTime) < (3 * 1000)) {
                finish();
            } else {
                mBackPressedTime = curTime;
                Toast.makeText(this, "再次点击退出应用", Toast.LENGTH_LONG).show();
            }
        } else {
            finish();
        }
    }

    View createView(int drawable, String txt) {
        View view = getLayoutInflater().inflate(R.layout.tab_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.nav_iv_icon);
        TextView textView = (TextView) view.findViewById(R.id.nav_tv_title);
        imageView.setImageResource(drawable);
        textView.setText(txt);
        return view;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        FragmentManager manager = getSupportFragmentManager();
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        quick_option_iv = (ImageView) findViewById(R.id.quick_option_iv);
        quick_option_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TweetPublishActivity.class));
            }
        });
        fragmentTabHost.setup(this, manager, R.id.main_container);

        mTabWidget = fragmentTabHost.getTabWidget();
        mTabWidget.setDividerDrawable(null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("首页").setIndicator(createView(R.drawable.tab_icon_main, "首页")), DynamicTabFragment.class, null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("任务中心").setIndicator(createView(R.drawable.tab_icon_mission, "任务中心")), MissionCenterFragment.class, null);

//        View view = createView(R.drawable.tab_icon_new, "本地");
//        view.setVisibility(View.INVISIBLE);
//        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("本地").setIndicator(view), MainFragment.class, null);

        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("邀请").setIndicator(createView(R.drawable.tab_icon_invitation, "邀请")), InvitationFragment.class, null);
        fragmentTabHost.addTab(fragmentTabHost.newTabSpec("个人中心").setIndicator(createView(R.drawable.tab_icon_me, "个人中心")), MeFragment.class, null);

        mTabWidget.getChildAt(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentTabHost.getCurrentTab() != 2){
//                    User user = AccountHelper.instance(AppContext.getInstance()).getUser();
//                    if (user == null) {
//                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                    } else {
                        fragmentTabHost.setCurrentTab(2);
//                    }
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mListener != null) {
            mListener.onResult(requestCode, resultCode, data);
            mListener = null;
        }
    }

    public interface OnActivityResultListener {
        void onResult(int requestCode, int resultCode, Intent data);
    }
}
