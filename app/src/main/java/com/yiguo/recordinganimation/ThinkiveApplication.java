package com.yiguo.recordinganimation;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.yiguo.recordinganimation.KeepLive.KeepLive;
import com.yiguo.recordinganimation.KeepLive.config.ForegroundNotification;
import com.yiguo.recordinganimation.KeepLive.config.ForegroundNotificationClickListener;
import com.yiguo.recordinganimation.KeepLive.config.KeepLiveService;

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
        //定义前台服务的默认样式。即标题、描述和图标
        ForegroundNotification foregroundNotification = new ForegroundNotification("测试", "描述", R.mipmap.ic_launcher,
                //定义前台服务的通知点击事件
                new ForegroundNotificationClickListener() {
                    @Override
                    public void foregroundNotificationClick() {
                        Log.d(TAG,"app通知点击事件...");

                    }

                });
        //启动保活服务
        KeepLive.startWork(this, null,
                //你需要保活的服务，如socket连接、定时任务等，建议不用匿名内部类的方式在这里写
                new KeepLiveService() {
                    @Override
                    public void onWorking(Context context) {
                        Log.d(TAG,"app正在工作...");
                    }

                    @Override
                    public void onStop(Context context) {

                    }
                }
        );
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
