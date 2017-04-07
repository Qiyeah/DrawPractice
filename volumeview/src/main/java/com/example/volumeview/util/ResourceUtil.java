package com.example.volumeview.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by sl on 2017/3/31.
 */

public class ResourceUtil {
    public static int getColor(Context context,int rid){
        return ContextCompat.getColor(context,rid);
    }
}
