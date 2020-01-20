/*
 * Copyright (c) DAR Ecosystem 2018.
 *
 * Created by sough on 09/07/2018.
 */

package com.asset.data.util;

import android.util.Log;

import com.asset.data.BuildConfig;


/**
 * Created by sough on 17/10/2017.
 */

public class Logger {

    public static void msg(Object msg) {
        if(BuildConfig.DEBUG) {
            Log.i("MSG", msg + "");
        }
    }

    public static void api(String msg) {
        if(BuildConfig.DEBUG) {
            Log.i("API", msg);
        }
    }

    public static void msg(String tag, Object msg) {
        if(BuildConfig.DEBUG) {
            Log.i(tag, msg + "");
        }
    }
}
