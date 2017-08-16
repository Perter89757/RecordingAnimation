package com.yiguo.recordinganimation.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DownLoadService extends Service {
    public DownLoadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new downBinder();
    }

    class downBinder extends Binder {
        public int startdown() {
            return 0;
        }

        public void pauseDown() {
        }

        public void stopDown() {
        }

        public void errorDown() {
        }

        public void successDown() {
        }
    }


}
