package com.yiguo.recordinganimation.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.util.SparseArray;

import com.yiguo.recordinganimation.callback.CallBack;

/**
 * author: huang_yanhui
 * data:2017/8/18
 * time:16:28
 * emaill:huangyh@thinkive.com
 * description:
 */

class tkCallBack implements CallBack {

    private static SparseArray<CallBack> callbackMap = new SparseArray<>();
    private static int key;
    public tkCallBack(CallBack callBack) {
        callbackMap.put(key++,callBack);
    }

    public static void registerBackReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.push.remoteService_message");
        context.registerReceiver(callbackReceiver, intentFilter);
    }

    private static CustomReciver callbackReceiver = new CustomReciver();

    @Override
    public void onsuceess() {

    }

    @Override
    public void onerror(String stirng) {

    }



    public static class CustomReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.push.remoteService_message")) {
                String data = intent.getStringExtra("data");

                Log.d("TAG","收到广播:"+data);
                CallBack callBack = callbackMap.get(0);
                if (callBack != null){
                    callBack.onerror(data);
                }
            }
        }
    }
}
