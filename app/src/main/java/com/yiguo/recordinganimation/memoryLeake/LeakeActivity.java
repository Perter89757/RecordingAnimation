package com.yiguo.recordinganimation.memoryLeake;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yiguo.recordinganimation.R;

import java.util.ArrayList;

public class LeakeActivity extends AppCompatActivity {
    private Handler mLeakHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leake);
        //发送延时消息
        mLeakHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 1000 *60 *10);
        finish();
    }


}
