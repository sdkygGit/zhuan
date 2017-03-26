package com.zhuanworld.dev.utils;

import android.util.Log;

import com.zhuanworld.dev.AppConfig;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
public class Logger {

    public static boolean DEBUG = AppConfig.DEBUG;

    public static void d(String tag, String msg) {
        if (DEBUG)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, msg);
    }
}
