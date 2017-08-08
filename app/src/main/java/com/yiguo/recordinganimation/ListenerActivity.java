package com.yiguo.recordinganimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import thinkive.com.mylibrary.TKNotifier;
import thinkive.com.twolibrary.ToolsUtils;

public class ListenerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);
        initData();
        findViewById(R.id.getData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = TKNotifier.getInstance().iconApp();
                Log.d("TAG",""+s);
                String appName = ToolsUtils.getInstance().getAppName();
                Log.d("TAG","name:"+appName);
            }
        });
    }

    private void initData() {
        listener listener = new listener();
        //设置对象到接口类中,这个对象携带了数据
        ToolsUtils.getInstance().setWaiCengListener(listener);
    }

    class listener implements ToolsUtils.waiCengListener {

        @Override
        public String geticon() {
            return "app模块的数据-->lib模块";
        }

        @Override
        public String getName() {
            return "jack_tom";
        }

        @Override
        public void setFlag(String s) {

        }
    }

}
