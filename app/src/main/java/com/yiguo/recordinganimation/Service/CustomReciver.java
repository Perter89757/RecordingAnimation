package com.yiguo.recordinganimation.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


/**
 * author: huang_yanhui
 * data:2017/8/16
 * time:17:45
 * emaill:huangyh@thinkive.com
 * description:
 */

public class CustomReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.intent.click_notification")) {
            Intent startService = new Intent(context, DownLoadService.class);
            context.startService(startService);
        }
    }
}
