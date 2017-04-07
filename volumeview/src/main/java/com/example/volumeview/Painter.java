package com.example.volumeview;

import android.graphics.Canvas;

/**
 * Created by sl on 2017/3/23.
 */

public interface Painter {
    void draw(Canvas canvas);
    void onSizeChanged(int w, int h);
}
