package com.example.volumeview.broken_painter.grid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.volumeview.R;
import com.example.volumeview.broken_painter.constract.BrokenConstant;
import com.example.volumeview.util.DisplayUtil;

/**
 * Created by sl on 2017/3/31.
 */

public class GridPainterImp implements GridLinePainter {
    private int max;
    private Context context;
    private float blank;
    private float width, height;
    private float start, end;

    private Paint paint;
    private Path path;
    private float strokeWidth;
    private float top;
    private float bottom;

    public GridPainterImp(Context context, int max) {
        this.max = max;
        this.context = context;
    }

    @Override
    public void draw(Canvas canvas) {
        initPaint();
        initPath();
        canvas.drawPath(path, paint);
    }

    private void initPath() {
        path = new Path();
        for (int i = 0; i < max*5 + 1; i++) {
            path.moveTo(start+blank*i,top);
            path.lineTo(start+blank*i,bottom);

        }
        for (int i = 0; i < (int) ((bottom-top)/blank) + 1; i++) {
            path.moveTo(start, start + blank * i+top-blank);
            path.lineTo(end, start + blank * i+top-blank);
        }
    }

    private void initPaint() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(ContextCompat.getColor(context, R.color.light_green));
        paint.setAntiAlias(true);
    }

    @Override
    public void onSizeChanged(int w, int h) {
        width = w;
        height = h;
        initSize();
    }

    private void initSize() {
        blank = (width - BrokenConstant.MARGIN * 2) / max/5;
        start = (width - blank*5 * max) / 2;
        end = blank*5 * max + start;
        top = 0;
        bottom = height - BrokenConstant.MARGIN;
        strokeWidth = DisplayUtil.dip2px(context, 0.5f);
        Log.e("TAG", "initSize: width = "+width);
        Log.e("TAG", "initSize: height = "+height);
        Log.e("TAG", "initSize: blank = "+blank);
        Log.e("TAG", "initSize: start = "+start);
        Log.e("TAG", "initSize: end = "+end);
        Log.e("TAG", "initSize: bottom = "+bottom);
        Log.e("TAG", "initSize: top = "+top);
    }
}
