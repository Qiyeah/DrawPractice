package com.example.volumeview.broken_painter.brokenline;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.volumeview.R;
import com.example.volumeview.broken_painter.constract.BrokenConstant;
import com.example.volumeview.util.DisplayUtil;
import com.example.volumeview.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sl on 2017/3/28.
 */

public class LinePainterImp implements LinePainter {
    private Paint paint;
    private int width;
    private int height;

    private float centerX, centerY;
    private int index;
    private List<Float> points = new ArrayList<>();
    private Path path;
    private Context context;
    private int strokeWidth;
    private int blank;
    private int max;
    private int count;

    /**
     *
     */
    public LinePainterImp(Context context, int max) {
        this.context = context;
        this.max = max;
        initPaint();
    }


    @Override
    public void setValue(float value) {
        if (centerY - value > 0) {
            points.add(centerY - value);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        path = new Path();
        if (count < 1) {
            points.add(centerY);
            count++;
        }
      /*  for (int i = 0; i < max+1; i++) {
            canvas.drawLine(BrokenConstant.MARGIN+blank*i,BrokenConstant.MARGIN,BrokenConstant.MARGIN+blank*i,BrokenConstant.MARGIN+blank*(max),paint);
        }

        for (int i = 0; i <max+1; i++) {
            canvas.drawLine(BrokenConstant.MARGIN,BrokenConstant.MARGIN+blank*i,blank*(max)+BrokenConstant.MARGIN,BrokenConstant.MARGIN+blank*i,paint);
        }*/
        path.moveTo(BrokenConstant.MARGIN, points.get(0));
        paint.setStrokeWidth(1f);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(BrokenConstant.MARGIN, points.get(0), 3, paint);
        for (int i = 1; i < points.size(); i++) {
            path.lineTo(i * blank + BrokenConstant.MARGIN, points.get(i));
            canvas.drawCircle(i * blank + BrokenConstant.MARGIN, points.get(i), 3, paint);
        }
        paint.setColor(ContextCompat.getColor(context, R.color.dark_green));

        paint.setStrokeWidth(2f);
        canvas.drawPath(path, paint);

        if (points.size() > max + 1) {
            points.remove(0);
//            canvas.translate(-lastValue, 0);
        }
    }

    @Override
    public void onSizeChanged(int w, int h) {


        width = w;
        height = h;

        blank = (width - BrokenConstant.MARGIN * 2) / max;
        centerX = (width - BrokenConstant.MARGIN * 2) / 2;
        centerY = blank * max / 2 + BrokenConstant.MARGIN;
        strokeWidth = DisplayUtil.dip2px(context, 1.2f);
        initSize();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(context.getResources().getColor(R.color.light_green));
        paint.setAntiAlias(true);
    }

    private void initSize() {
        strokeWidth = DisplayUtil.dip2px(context, 0.5f);
    }


}
