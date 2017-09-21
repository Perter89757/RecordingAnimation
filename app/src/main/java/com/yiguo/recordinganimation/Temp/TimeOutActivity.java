package com.yiguo.recordinganimation.Temp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yiguo.recordinganimation.R;
import com.yiguo.recordinganimation.callback.CallBack;

import java.util.Timer;
import java.util.TimerTask;

public class TimeOutActivity extends AppCompatActivity {
    Object o = new Object();
    Boolean isTimeout = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                //超时
                if (isTimeout) {
                    Log.d("TAG", "收到超时提醒,执行超时任务");
                    isTimeout = false;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView view = (TextView) findViewById(R.id.tv1);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行任务,计时开始
                checkTimeOut();
                isTimeout = true;
                Log.d("TAG", "开始执行任务,计时开始");
            }
        });

        findViewById(R.id.notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTimeout = false;
                Log.d("TAG", "任务完成");
            }
        });

    }

    private void checkTimeOut() {
        Timer timer = new Timer();
        runTimeTask task = new runTimeTask();
        //计时器每五秒执行这个任务
        timer.schedule(task, 5000);
    }

    class runTimeTask extends TimerTask {

        @Override
        public void run() {
            handler.sendEmptyMessage(1);
        }
    }

    public void startThread(final CallBack callBack) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (o) {
                    try {
                        Log.d("TAG", "线程is wait");
                        o.wait();
                        Log.d("TAG", "线程is run");
                        callBack.onsuceess("");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}

