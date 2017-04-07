package com.example.volumeview.painter.needle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.volumeview.util.DisplayUtil;


/**
 * Created by sl on 2017/3/22.
 */

public class NeedlePainterImpl implements NeedlePainter{
    protected Paint paint;
    private Context mContext;
    private int color = Color.RED;
    private int width;
    private int height;
    private int centerX;
    private int centerY;
    private int margin;
    private int startAngle = 0;
    private float plusAngle = 0;
    private float max;
    private int strokeWidth;
    private float maxAngle = 180;
    private int radius;
    public static final String TAG = "TAG";
    public NeedlePainterImpl(float max, Context context) {
        this.max = max;
        mContext = context;
        init();
    }

    private void init() {
        initSize();
        initPainter();
    }
    private void initSize() {
        margin = DisplayUtil.dip2px(mContext,15);
        strokeWidth = DisplayUtil.dip2px(mContext,2);
    }

    private void initPainter() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
    }


    public void draw(Canvas canvas) {
        float dx = 0;
        float dy = 0;
        canvas.rotate(-180,centerX,centerY);
        dx = (float) (centerX - radius * Math.cos(Math.toRadians(startAngle + plusAngle)));
        dy =(float) (centerY - radius * Math.sin(Math.toRadians(startAngle + plusAngle)));
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.RED);
        canvas.drawLine(centerX, centerY, dx, dy, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        canvas.drawCircle(centerX, centerY,10, paint);
    }

    public void setValue(float value) {
        plusAngle = (maxAngle * value) / max;
    }

    public void onSizeChanged(int w, int h) {
        height = h;
        width = w;
        radius = width / 2 - width / 5;
        centerY = (height + width / 2 - width / 7)/2;
        centerX = width/ 2;
    }
}
