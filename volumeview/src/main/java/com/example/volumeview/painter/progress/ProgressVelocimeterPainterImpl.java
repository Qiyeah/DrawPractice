package com.example.volumeview.painter.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.volumeview.util.DisplayUtil;
import com.example.volumeview.util.LogUtil;


/**
 * Created by sl on 2017/3/23.
 */

public class ProgressVelocimeterPainterImpl implements ProgressVelocimeterPainter {
    private Context mContext;
    private Paint mPaint;
    private int width;
    private int height;
    private int startAngle = 0;
    private float plusAngle = 0;
    private float centerX,centerY;
    private float radius;
    private int strokeWidth;
    private float lineWidth;
    private float lineSpace;
    private float maxAngle = 180;
    private float max = 100;
    private RectF circle;


    public ProgressVelocimeterPainterImpl(Context context) {
        mContext = context;

        init();
    }

    private void init() {
        initPainter();
    }

    @Override
    public void setValue(float value) {
        plusAngle = (maxAngle * value) / max;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.rotate(-180,centerX,centerY);
        canvas.drawArc(circle, startAngle, plusAngle+startAngle, false, mPaint);
    }

    @Override
    public void onSizeChanged(int width, int height) {
        this.width = width;
        this.height = height;
        initSize();
        initExternalCircle();
    }

    private void initSize() {
        radius = width / 2 - width / 7;
        centerY = (height+radius)/2;

        centerX = width / 2;
        strokeWidth = DisplayUtil.dip2px(mContext,20);
        lineWidth = DisplayUtil.dip2px(mContext,6);
        lineSpace = DisplayUtil.dip2px(mContext,2);

    }
    private void initPainter(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.GREEN);
        mPaint.setPathEffect(new DashPathEffect(new float[]{ 5, 4 },0));
    }

    private void initExternalCircle() {
        circle = new RectF(centerX-radius-50,centerY-radius-50,centerX+radius+50,centerY+radius+50);
    }
}
