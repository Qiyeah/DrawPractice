package com.example.sl.drawpractice;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.volumeview.BrokenLine;
import com.example.volumeview.util.LogUtil;
import com.example.volumeview.CircleView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private CircleView circle;
    private BrokenLine broken;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            float value = (float) (Math.random() * 100);
            // LogUtil.e("value = "+value,true);
            circle.setValue(value, true);
            broken.setValue(value,true);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        circle = (CircleView) findViewById(R.id.circle);
        circle.setValue(100, true);
        broken = (BrokenLine) findViewById(R.id.broken);
        broken.setValue(100, true);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(1);
            }
        };
        timer.schedule(task, 2000, 2000);


    }
}
