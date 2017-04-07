package com.example.volumeview.util;

import android.view.View;

/**
 * Created by sl on 2017/3/21.
 */

public class MeasureUtil {

    public static int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int specMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int specSize = View.MeasureSpec.getSize(heightMeasureSpec);

        if (View.MeasureSpec.EXACTLY == specMode) {
            result = specSize;
        } else {
            result = 200;
            if (View.MeasureSpec.AT_MOST == specMode) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    public static int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int specMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int specSize = View.MeasureSpec.getSize(widthMeasureSpec);

        if (View.MeasureSpec.EXACTLY == specMode) {
            result = specSize;
        } else {
            result = 200;
            if (View.MeasureSpec.AT_MOST == specMode) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
