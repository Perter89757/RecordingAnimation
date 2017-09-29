package com.yiguo.recordinganimation.eventBus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yiguo.recordinganimation.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {

    private TextView messageRevice;
    private Button startC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        messageRevice = (TextView) findViewById(R.id.messageRevice);
        startC = (Button) findViewById(R.id.startC);
        startC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventBusActivity.this,EventBusCActivity.class));
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    //订阅
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessage(MessageEvent event) {
//       // SystemClock.sleep(7000);
//        messageRevice.setText(event.message);
//    }
    //在onStart调用register后，执行消息
    @Subscribe(priority = 6,sticky = true, threadMode = ThreadMode.POSTING)
    public void onEvent(MessageEvent event) {
        messageRevice.setText(event.message);

        MessageEvent stickyEvent = EventBus.getDefault().getStickyEvent(MessageEvent.class);
        if(stickyEvent != null) {
            EventBus.getDefault().removeStickyEvent(stickyEvent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
