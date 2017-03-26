package com.zhuanworld.dev.account;

import android.annotation.SuppressLint;
import android.app.Application;

import com.zhuanworld.dev.bean.User;
import com.zhuanworld.dev.helper.SharedPreferencesHelper;
import com.zhuanworld.dev.utils.TLog;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
public class AccountHelper {

    private User user;
    private Application application;
    @SuppressLint("StaticFieldLeak")
    private static AccountHelper instances;

    private AccountHelper(Application application) {
        this.application = application;
    }

    public static AccountHelper instance(Application application) {
        if (instances == null) {
            synchronized (AccountHelper.class) {
                instances = new AccountHelper(application);
            }
        }
        return instances;
    }

    public synchronized User getUser() {
        if (instances.user == null)
            instances.user = SharedPreferencesHelper.loadFormSource(instances.application, User.class);
        return instances.user;
    }

    public String getCookie() {

        String cookie = null;
        try {
            cookie = getUser().getCookie();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookie == null ? "" : cookie;
    }

}
