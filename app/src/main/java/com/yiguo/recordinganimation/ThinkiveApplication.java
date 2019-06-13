package com.yiguo.recordinganimation;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;


/**
 * author: huang_yanhui
 * data:2017/6/8
 * time:9:42
 * emaill:huangyh@thinkive.com
 * description:
 */

public class ThinkiveApplication extends Application {
    private final static String TAG = "ThinkiveApplication";
    private BroadcastReceiver receiver;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {//1
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
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
