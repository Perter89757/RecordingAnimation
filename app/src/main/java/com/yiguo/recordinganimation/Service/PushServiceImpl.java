package com.yiguo.recordinganimation.Service;

/*
 *  @项目名：  RecordingAnimation 
 *  @包名：    com.yiguo.recordinganimation.Service
 *  @文件名:   PushServiceImpl
 *  @创建者:   HuangYanHui
 *  @创建时间:  2017/8/17 0017 21:56
 *  @描述：    TODO
 */

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.yiguo.recordinganimation.callback.CallBack;

/**
 * service的实现类
 */
public class PushServiceImpl implements IPushService {


    @Override
    public void login(int userName, CallBack callBack) {

        //service 处理的结果返回给activity
        //采用message发送,或者是broadcast
    }


    private int FormActivity;
    Messenger messenger_Service = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgFormActivity) {
            int what = msgFormActivity.what;
            Message message = Message.obtain(msgFormActivity);
            switch (what) {
                case 1:
                    FormActivity = msgFormActivity.arg1;
                    message.arg1 = FormActivity + 123;
                    Log.d("TAG","service收到数据:"+FormActivity);
                    try {
                        msgFormActivity.replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    });


    public IBinder getBinder() {
        return messenger_Service.getBinder();
    }
}
