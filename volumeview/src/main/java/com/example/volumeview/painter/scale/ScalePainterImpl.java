package com.example.volumeview.painter.scale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.volumeview.util.DisplayUtil;
import com.example.volumeview.util.LogUtil;


/**
 * Created by sl on 2017/3/23.
 */

public class ScalePainterImpl implements ScalePainter {
    private Context mContext;
    private Paint paint;
    private float centerX, centerY, radius, maxAngle;
    private int width;
    private int height;
    private float strokeWidth;
    private float scaleAngle = 3;
    private int max = 60;


    public ScalePainterImpl(Context context) {
        mContext = context;
        initSize();
        initPainter();

    }

    @Override
    public void initPainter() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(strokeWidth);
    }


    public void initSize() {
        radius = width / 2 - width / 7;
        centerY = (height +radius) / 2;
        centerX = (width) / 2;
        strokeWidth = DisplayUtil.dip2px(mContext, 0.1f);

    }
    @Override
    public void draw(Canvas canvas) {
        float dx = 0;
        float dy = 0;

        canvas.rotate(-90, centerX, centerY);

        for (int i = 0; i < max+1; i++) {
            if (i%10 == 0){
                paint.setTextSize(30);
                canvas.drawLine(centerX, centerY - radius - 20, centerX, centerY - radius + 10, paint);
                canvas.drawText(String.valueOf(i*0.5/10), centerX - 20, centerY - radius + 40, paint);
            }else {
                canvas.drawLine(centerX, centerY - radius - 20, centerX, centerY - radius, paint);
            }
            canvas.rotate(scaleAngle, centerX, centerY);


        }
        canvas.rotate(-(scaleAngle * (max+1) - 90), centerX, centerY);
    }

    @Override
    public void onSizeChanged(int width, int height) {
        this.width = width;
        this.height = height;

        initSize();
    }

}
