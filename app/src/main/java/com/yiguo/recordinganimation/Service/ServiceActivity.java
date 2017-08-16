package com.yiguo.recordinganimation.Service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yiguo.recordinganimation.R;

public class ServiceActivity extends AppCompatActivity {

    private TextView mTvState;
    private Button mBtnAdd;
    private LinearLayout mLyContainer;
    private Button mBtstart;
    private Button mBtdownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        mTvState = (TextView) findViewById(R.id.id_tv_callback);
        mBtnAdd = (Button) findViewById(R.id.id_btn_add);
        mBtstart = (Button) findViewById(R.id.id_btn_start);
        mBtdownload = (Button) findViewById(R.id.id_btn_download);

        mLyContainer = (LinearLayout) findViewById(R.id.id_ll_container);

        mBtstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this,MyService.class);
                bindService(intent, mConn, Context.BIND_AUTO_CREATE);
                Log.e("TAG", "bindService invoked !");
            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取Message对象
                Message msg = Message.obtain(null,MSG_SUM);
                msg.obj = "我是界面";
                msg.replyTo = ClientMessenger;
                if (isConn){
                    try {
                        getServiceMessager.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        mBtdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceActivity.this,DownLoadService.class);
                bindService(intent,downloadServiceContect,Context.BIND_AUTO_CREATE);

                //点击弹出通知栏,通知栏点击下载
                showNotify();
                //

                int downProgress = service1.startdown();

            }


        });
    }

    private void showNotify() {
        String action="com.intent.click_notification";
        Intent intent = new Intent(action);
        //PendingIntent主要用来处理即将发生的事,相当于Intent的延时,在这里是用来发送广播通知
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        Notification notification = builder
                .setContentTitle("更新通知")//标题
                .setContentText("QQ3.233已正式发布请您及时更新,\n新版本将为您带来不一样的体验！")//内容
                .setWhen(System.currentTimeMillis())//通知时间，系统时间
                .setSmallIcon(R.mipmap.ic_launcher)//标题栏上显示的通知icon
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//通知显示的icon
                .setDefaults(Notification.DEFAULT_VIBRATE)//DEFAULT_VIBRATE默认震动，DEFAULT_SOUND默认声音,DEFAULT_LIGHTS默认灯光
                .setColor(Color.parseColor("#98903B"))//smallIcon的背景色
                .setContentIntent(pendingIntent)
                .build();
        manager.notify(1, notification);
    }


    private DownLoadService.downBinder service1;
    private Messenger getServiceMessager;
    private boolean isConn;
    private static final int MSG_SUM = 0x110;
    private Messenger ClientMessenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msgFormService) {
            int what = msgFormService.what;
            switch (what) {
                case MSG_SUM:
                    Log.d("TAG", "来自service:"+(String) msgFormService.obj);
                    break;
            }
        }
    });

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            getServiceMessager = new Messenger(service);
            isConn = true;
            mTvState.setText("connected!");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            getServiceMessager = null;
            isConn = false;
            mTvState.setText("disconnected!");
        }
    };

    private ServiceConnection downloadServiceContect = new ServiceConnection() {



        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            service1 = (DownLoadService.downBinder) (service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
