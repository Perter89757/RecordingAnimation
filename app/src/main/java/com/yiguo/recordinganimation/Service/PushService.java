package com.yiguo.recordinganimation.Service;

/*
 *  @项目名：  RecordingAnimation 
 *  @包名：    com.yiguo.recordinganimation.Service
 *  @文件名:   PushService
 *  @创建者:   HuangYanHui
 *  @创建时间:  2017/8/17 0017 21:59
 *  @描述：    TODO
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class PushService extends Service {

    private PushServiceImpl service;

    @Override
    public void onCreate() {
        super.onCreate();
        service = new PushServiceImpl(this);

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return service.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }
}
