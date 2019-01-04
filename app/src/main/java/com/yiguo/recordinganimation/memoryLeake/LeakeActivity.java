package com.yiguo.recordinganimation.memoryLeake;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yiguo.recordinganimation.R;

import java.util.ArrayList;

public class LeakeActivity extends AppCompatActivity {

    private TextView content;
    private ArrayList<String> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leake);
        content = (TextView) findViewById(R.id.content);
        list = new ArrayList<>();

        list.add(0,"");
        list.add(1,"");
    }

    public void A(View view) {
        //延时2s
        String string = "";
        SystemClock.sleep(2000);
        list.add(0,"中国");
        for (int i = 0; i < 2; i++) {
            string += list.get(i);
        }
        content.setText(string);
    }

    public void B(View view) {
        String string = "";
        list.add(1,"深圳");
         for (int i = 0; i <2; i++) {
            string += list.get(i);
        }
        content.setText(string);
    }
}
