package com.example.volumeview.painter.inside;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.volumeview.util.DisplayUtil;

/**
 * Created by sl on 2017/3/23.
 */

public class InsideVelocimeterPainterImpl implements InsideVelocimeterPainter {
    private Paint mPaint;
    private Context mContext;
    private RectF circleRectF;
    private int width;
    private int height;
    private float  startAngle = 180;
    private float finishAngle = 180;
    private int strokeWidth;
    private int externalStrokeWidth;
    private int blurMargin;
    private int margin;
    private int centerY;
    private int centerX;
    private int radius;

    public InsideVelocimeterPainterImpl(Context context) {
        mContext = context;
        initSize();
        initPainter();
        initCircle();
    }



    @Override
    public void initPainter() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void initSize() {
        this.blurMargin = DisplayUtil.dip2px( mContext,15);
        this.externalStrokeWidth =  DisplayUtil.dip2px( mContext,20);
        this.strokeWidth =  DisplayUtil.dip2px( mContext,0.8f);
        this.margin =  DisplayUtil.dip2px( mContext,9);
    }
    boolean isShow = false;
    @Override
    public void initCircle() {
        isShow = false;
        circleRectF = new RectF(centerX-radius-20,centerY-radius-20,centerX+radius+20,centerY+radius+20);
    }

    @Override
    public void draw(Canvas canvas) {
        isShow = true;
        canvas.drawArc(circleRectF,startAngle,finishAngle,false,mPaint);
    }

    @Override
    public void onSizeChanged(int width, int height) {
        this.width = width;
        this.height = height;
        radius = width / 2 - width / 7;
//        centerY = height - height / 7;
        centerY = (height +radius)/2;

        centerX = (width) / 2;
        initCircle();
    }
}
