package com.yiguo.recordinganimation;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by huang_yanhui on 2019/6/12.
 */

public class TestService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("test","TestService被创建");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
