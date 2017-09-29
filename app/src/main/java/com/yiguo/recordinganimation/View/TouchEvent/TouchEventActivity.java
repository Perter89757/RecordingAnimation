package com.yiguo.recordinganimation.View.TouchEvent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.yiguo.recordinganimation.R;

public class TouchEventActivity extends AppCompatActivity {

    private RelativeLayout rootLayout;
    private MyLinearLayout textViewLayout;
    private MyTextView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        textViewLayout = (MyLinearLayout) findViewById(R.id.textViewLayout);
        viewById = (MyTextView) findViewById(R.id.textView);

        rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("event","root响应点击");
            }
        });


        textViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("event","textViewLayout响应点击");
            }
        });

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("event","textView响应点击");
            }
        });
    }
}
