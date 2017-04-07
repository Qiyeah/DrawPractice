package com.example.volumeview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.volumeview.broken_painter.brokenline.LinePainterImp;
import com.example.volumeview.broken_painter.constract.BrokenConstant;
import com.example.volumeview.broken_painter.grid.GridLinePainter;
import com.example.volumeview.broken_painter.grid.GridPainterImp;
import com.example.volumeview.util.LogUtil;
import com.example.volumeview.util.MeasureUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sl on 2017/3/28.
 */

public class BrokenLine extends View implements BaseView {

    private Paint paint;
    private Path path;

    private int max;

    private int width;
    private int height;
    private int blank = 100;
    private int offset;
    private List<Float> points = new ArrayList<>();
    private int index = 0;

    private float value;
    private double angle;
    private float xAxis, yAxis;
    private float centerX,centerY;

    private float lastValue;
    private ValueAnimator lineValueAnimator;

    private LinePainterImp linePainter;
    private GridLinePainter gridPainter;

    public BrokenLine(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }


    @Override
    public void init(Context context, AttributeSet attributeSet) {
        TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.BrokenLine);
        initAttributes(array);

        initValueAnimator();
        initPainter();

    }

    private void initPainter() {
        linePainter = new LinePainterImp(getContext(),max);
        gridPainter = new GridPainterImp(getContext(),max);
    }

    @Override
    public void initAttributes(TypedArray attributes) {
        max = attributes.getInt(R.styleable.BrokenLine_points, 12);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureUtil.measureWidth(widthMeasureSpec),MeasureUtil.measureHeight(heightMeasureSpec));
    }
    int count = 0;
    @Override
    protected void onDraw(Canvas canvas) {

        gridPainter.draw(canvas);
        linePainter.draw(canvas);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        linePainter.onSizeChanged(w,h);
        gridPainter.onSizeChanged(w,w);
    }

    public void setValue(float value, boolean animate) {

        linePainter.setValue(value);
     /*  // this.value = centerY - value;
       if (centerY - value>0){
           this.value = centerY - value;
           points.add(this.value);
       }
        // angle = Math.atan2(value, blank);
        if (!animate) {

        } else {
            animateProgressValue();
        }*/
    }


    private void initValueAnimator() {
        lineValueAnimator = new ValueAnimator();
        lineValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        lineValueAnimator.addUpdateListener(new BrokenLine.LineAnimatorValueListener());

    }

    private void animateProgressValue() {
        //LogUtil.e("" + (xAxis) + " , " + (index + 1) * blank, true);
        lineValueAnimator.setFloatValues(0, blank);
        lineValueAnimator.setDuration(3000);
        lineValueAnimator.start();
    }


    private class LineAnimatorValueListener implements ValueAnimator.AnimatorUpdateListener {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float value = (float) animation.getAnimatedValue();
            lastValue = value;
        }
    }
   /* private void updateValueProgress(float realValue){

    }*/
}
