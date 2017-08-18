package com.yiguo.recordinganimation.Service;

/*
 *  @项目名：  RecordingAnimation 
 *  @包名：    com.yiguo.recordinganimation.Service
 *  @文件名:   PushServiceImpl
 *  @创建者:   HuangYanHui
 *  @创建时间:  2017/8/17 0017 21:56
 *  @描述：    TODO
 */

import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import com.yiguo.recordinganimation.callback.CallBack;

/**
 * service的实现类
 */
public class PushServiceImpl implements IPushService {
    private PushService pushService;

    public PushServiceImpl(PushService service) {
        this.pushService = service;
    }

    @Override
    public void login(int userName, CallBack callBack) {

        //service 处理的结果返回给activity
        //采用message发送,或者是broadcast

        callBack.onerror("登录成功");
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
                    Log.d("TAG", "service收到数据:" + FormActivity + "进行登录");
                    login(FormActivity, new CallBack() {
                        @Override
                        public void onsuceess() {

                        }

                        @Override
                        public void onerror(String stirng) {
                            Log.d("TAG", "service登录:" + stirng);
                            // 方案一:通过广播
                            //   sendBroadCoast(stirng);
                        }
                    });
                    //方案二:通过信使
//                    message.arg1 = FormActivity + 1;
//                    try {
//                        msgFormActivity.replyTo.send(message);
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
                    break;
            }

        }
    });

    private void sendBroadCoast(String stirng) {
        Intent intent = new Intent("com.push.remoteService_message");
        intent.putExtra("data", stirng);
        pushService.sendBroadcast(intent);
    }


    public IBinder getBinder() {
        return messenger_Service.getBinder();
    }
}
