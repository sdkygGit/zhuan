package com.zhuanworld.dev;

import com.zhuanworld.dev.account.AccountHelper;
import com.zhuanworld.dev.api.ApiHttpClient;
import com.zhuanworld.dev.detail.DetailCache;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
public class TKApplication extends AppContext {
    private static final String CONFIG_READ_STATE_PRE = "CONFIG_READ_STATE_PRE_";

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化操作
        DetailCache.init(getApplicationContext());
        init();
    }

    private void init() {
        // 初始化异常捕获类
        AppCrashHandler.getInstance().init(this);
        // 初始化网络请求
        ApiHttpClient.init(this);
        //初始化百度地图
//        SDKInitializer.initialize(this);
    }
}
