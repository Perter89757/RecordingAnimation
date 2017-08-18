package com.yiguo.recordinganimation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * author: huang_yanhui
 * data:2017/8/8
 * time:16:30
 * emaill:huangyh@thinkive.com
 * description:
 */

public class OnepxReceiver extends BroadcastReceiver {
    private static OnepxReceiver receiver;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Intent it = new Intent(context, HooliganActivity.class);
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(it);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            context.sendBroadcast(new Intent("finish activity"));
        }
    }

    public static void register1pxReceiver(Context context) {
        if (receiver == null) {
            receiver = new OnepxReceiver();
        }
        context.registerReceiver(receiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
        context.registerReceiver(receiver, new IntentFilter(Intent.ACTION_SCREEN_ON));
    }

    public static void unregister1pxReceiver(Context context) {
        context.unregisterReceiver(receiver);
    }
}