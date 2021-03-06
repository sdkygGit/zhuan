package com.zhuanworld.dev.utils;

import android.util.Log;

import com.zhuanworld.dev.AppConfig;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
public class TLog {
    private static final String LOG_TAG = "OSChinaLog";
    private static boolean DEBUG = AppConfig.DEBUG;

    private TLog() {
    }

    public static void error(String log) {
        if (DEBUG) Log.e(LOG_TAG, "" + log);
    }

    public static void log(String log) {
        if (DEBUG) Log.i(LOG_TAG, log);
    }

    public static void log(String tag, String log) {
        if (DEBUG) Log.i(tag, log);
    }

    public static void d(String tag, String log) {
        if (DEBUG) Log.d(tag, log);
    }

    public static void e(String tag, String log) {
        if (DEBUG) Log.e(tag, log);
    }

    public static void i(String tag, String log) {
        if (DEBUG) Log.i(tag, log);
    }
}
