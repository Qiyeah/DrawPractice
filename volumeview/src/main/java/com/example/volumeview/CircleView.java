package com.example.volumeview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;


import com.example.volumeview.painter.inside.InsideVelocimeterPainterImpl;
import com.example.volumeview.painter.needle.NeedlePainterImpl;
import com.example.volumeview.painter.progress.ProgressVelocimeterPainterImpl;
import com.example.volumeview.painter.scale.ScalePainterImpl;
import com.example.volumeview.util.MeasureUtil;

import static android.R.attr.max;

/**
 * Created by sl on 2017/3/21.
 */

public class CircleView extends View {
    int size;
    private float value = 0;
    private ValueAnimator nidleValueAnimator;
    private ValueAnimator progressValueAnimator;
    private float nidleLastValue = 0;
    private NeedlePainterImpl linePainter;
    private InsideVelocimeterPainterImpl circlePainter;
    private ScalePainterImpl mScalePainter;
    private ProgressVelocimeterPainterImpl mProgressVelocimeterPainter;
    private float min = 0;
    private float progressLastValue = min;

    private int height;
    private int width;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attributeSet) {
        TypedArray attributes =
                context.obtainStyledAttributes(attributeSet, R.styleable.CircleView);
        initAttributes(attributes);
        linePainter = new NeedlePainterImpl(100, getContext());
        circlePainter = new InsideVelocimeterPainterImpl(getContext());
        mScalePainter = new ScalePainterImpl(getContext());
        mProgressVelocimeterPainter = new ProgressVelocimeterPainterImpl(getContext());
        initValueAnimator();

    }

    private void initValueAnimator() {
        nidleValueAnimator = new ValueAnimator();
        nidleValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        nidleValueAnimator.addUpdateListener(new NeedleAnimatorListenerImp());
        progressValueAnimator = new ValueAnimator();
        progressValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        progressValueAnimator.addUpdateListener(new ProgressAnimatorListenerImp());
    }

    private void animateProgressValue() {
        nidleValueAnimator.setFloatValues(nidleLastValue, value);
        nidleValueAnimator.setDuration(3000);
        nidleValueAnimator.start();

        progressValueAnimator.setFloatValues(progressLastValue, value);
        progressValueAnimator.setDuration(3000);
        progressValueAnimator.start();
    }

    private void initAttributes(TypedArray attributes) {
        attributes.getString(R.styleable.CircleView_title);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        circlePainter.draw(canvas);
        mScalePainter.draw(canvas);
        mProgressVelocimeterPainter.draw(canvas);
        linePainter.draw(canvas);
        invalidate();
    }


    public void setValue(float value, boolean animate) {
        this.value = value;
        if (value <= max && value >= min) {
            if (!animate) {
                updateValueNeedle(value);
                updateValueProgress(value);
            } else {
                animateProgressValue();
            }
        }
    }

    private void updateValueProgress(float value) {
        mProgressVelocimeterPainter.setValue(value);
    }

    private void updateValueNeedle(float value) {
        linePainter.setValue(value);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(
                MeasureUtil.measureWidth(widthMeasureSpec),
                MeasureUtil.measureHeight(heightMeasureSpec));
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
        linePainter.onSizeChanged(w, h);
        circlePainter.onSizeChanged(w, h);
        mScalePainter.onSizeChanged(w, h);
        mProgressVelocimeterPainter.onSizeChanged(w, h);
    }

    public static final String TAG = "TAG";

    private class NeedleAnimatorListenerImp implements ValueAnimator.AnimatorUpdateListener {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Float value = (Float) valueAnimator.getAnimatedValue();
            updateValueNeedle(value);
            nidleLastValue = value;
        }
    }

    private class ProgressAnimatorListenerImp implements ValueAnimator.AnimatorUpdateListener {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Float value = (Float) valueAnimator.getAnimatedValue();
            updateValueProgress(value);
            progressLastValue = value;
        }
    }
}
