package com.yiguo.recordinganimation.Service;

/*
 *  @项目名：  RecordingAnimation 
 *  @包名：    com.yiguo.recordinganimation.Service
 *  @文件名:   PushServiceProxy
 *  @创建者:   HuangYanHui
 *  @创建时间:  2017/8/17 0017 21:57
 *  @描述：    activity的代理类
 */

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.yiguo.recordinganimation.callback.CallBack;

public class PushServiceProxy implements IPushService {

    private int formService;
    private final Messenger messenger_service;

    //将服务的信使传递过来
    public PushServiceProxy(IBinder service) {
        messenger_service = new Messenger(service);

    }

    @Override
    public void login(int userName, CallBack callBack) {
        Message message = Message.obtain(null,1);
        message.arg1 = userName;
        message.replyTo = messengerActivity;
        //服务器的信使
        try {
            messenger_service.send(message);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
     //   callBack.onerror("service返回数据:"+formService);
    }


    Messenger messengerActivity = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msgFormService) {
            int what = msgFormService.what;
            switch (what) {
                case 1:
                    formService = msgFormService.arg1;
                    Log.d("TAG","activity收到数据:"+formService);
                    break;
            }

        }
    });
}
