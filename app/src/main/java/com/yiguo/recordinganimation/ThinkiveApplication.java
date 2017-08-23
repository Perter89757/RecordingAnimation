package com.yiguo.recordinganimation;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.yiguo.recordinganimation.utils.FileUtils;
import com.yiguo.recordinganimation.utils.LogUtils;

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
        FileUtils.initFileDir(this);
        LogUtils.init(this);
        LogUtils.setLogLevel(LogUtils.LEVEL_DEBUG);
        //捕获异常崩溃,防止IM登录异常
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                LogUtils.e("uncaughtException",ex);
                //退出程序
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });

        //全局动态广播
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(action.equals("ddas")){
                    Log.d("TAG","接受到其他Activity通知");
                }
            }
        };
        registerReceiver(receiver,new IntentFilter("ddas"));


    }



    public static Context getAppContext() {
        return ThinkiveApplication.getAppContext();
    }
}
