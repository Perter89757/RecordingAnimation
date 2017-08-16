package com.yiguo.recordinganimation.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class MyService extends Service {
    public MyService() {
    }

    private static final int MSG_SUM = 0x110;

    //最好换成HandlerThread的形式
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgfromClient) {
            switch (msgfromClient.what) {
                //msg 客户端传来的消息
                case MSG_SUM:
                    try {
                        Message msg = Message.obtain(msgfromClient);
                        String obj = (String) msgfromClient.obj;
                        msg.what = MSG_SUM;
                        msg.obj = obj+",我是service";

                        msgfromClient.replyTo.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            super.handleMessage(msgfromClient);
        }
    });

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    public void load(){}
}
