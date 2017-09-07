package com.yiguo.recordinganimation.Service.loadServiceData;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * author: huang_yanhui
 * data:2017/9/6
 * time:16:23
 * emaill:huangyh@thinkive.com
 * description:
 */

public class ResponeDataBroadcardReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentService = new Intent(context,ResponseDataIntentService.class);
        intentService.putExtra("new_message",intent.getParcelableExtra("new_message"));
        intentService.putExtra("callback_key",intent.getStringExtra("callback_key"));
        context.startService(intentService);
        if (null != intent){
            Log.d("DouBanService","UI进程收到");
        }
    }
}
