package com.yiguo.recordinganimation.Service.loadServiceData;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.yiguo.recordinganimation.utils.LogUtils;

import okhttp3.OkHttpClient;

/**
 * author: huang_yanhui
 * data:2017/9/6
 * time:14:54
 * emaill:huangyh@thinkive.com
 * description:
 */

public class DouBanService extends Service {

    private OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("DouBanService","启动");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(null != intent && intent.hasExtra("cmdMessage")){
            LogUtils.d("DouBanService", "onStartCommand, intent is:" + intent);
            CmdMessage cmdMessage = (CmdMessage)intent.getParcelableExtra("cmdMessage");
            Intent intentRequset = new Intent(DouBanService.this,RequstDataService.class);
            intentRequset.putExtra("cmdMessage",cmdMessage);
            intentRequset.putExtra("callback_key",intent.getStringExtra("callback_key"));
            startService(intentRequset);
        }
        return Service.START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
