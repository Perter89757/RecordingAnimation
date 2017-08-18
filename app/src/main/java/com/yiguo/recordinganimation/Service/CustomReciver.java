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

    interface tkCallBack {
        void onsucess();

        void onerror(String s);
    }

    private tkCallBack tkCallBack;

    public void setTkCallBack(tkCallBack tkCallBack) {
        this.tkCallBack = tkCallBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.push.remoteService_message")) {
            String data = intent.getStringExtra("data");
            if (tkCallBack != null){
                tkCallBack.onerror(data);
            }
        }
    }
}
