package com.yiguo.recordinganimation;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.multidex.MultiDex;
import android.util.Log;

/**
 * author: huang_yanhui
 * data:2017/6/8
 * time:9:42
 * emaill:huangyh@thinkive.com
 * description:
 */

public class ThinkiveApplication extends Application {

    private BroadcastReceiver receiver;

    @Override
    public void onCreate() {
        super.onCreate();
        //全局动态广播
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("ddas")) {
                    Log.d("TAG", "接受到其他Activity通知");
                }
            }
        };
        registerReceiver(receiver, new IntentFilter("ddas"));


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getAppContext() {
        return ThinkiveApplication.getAppContext();
    }
}
