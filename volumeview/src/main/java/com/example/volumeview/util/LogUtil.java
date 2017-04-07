package com.example.volumeview.util;

import android.util.Log;

/**
 * Created by sl on 2017/3/23.
 */

public class LogUtil {
    public static boolean show = false;
    private static final String TAG = "TAG";
    public static void e(String info){
        if (show){
            Log.e(TAG,info);
        }
    }
    public static void e(String info,boolean isShow){
        if (isShow){
            Log.e(TAG,info);
        }
    }
    public static void i(String info){
        if (show)
            Log.i(TAG, info);
    }
    public static void d(String info){
        if (show)
            Log.d(TAG, info);
    }
    public static void w(String info){
        if (show)
            Log.w(TAG, info);
    }
}
