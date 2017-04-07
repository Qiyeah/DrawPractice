package com.example.volumeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * Created by sl on 2017/3/28.
 */

public interface BaseView {
    void init(Context context, AttributeSet attributeSet);
    void initAttributes(TypedArray attributes);
}
