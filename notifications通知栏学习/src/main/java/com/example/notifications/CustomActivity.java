package com.example.notifications;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.notifications.base.BaseActivity;

import static android.R.attr.id;

public class CustomActivity extends BaseActivity implements OnClickListener {
    /**
     * TAG
     */
    private final static String TAG = "CustomActivity";
    /**
     * 按钮：显示自定义通知
     */
    private Button btn_show_custom;
    /**
     * 按钮：显示自定义带按钮的通知
     */
    private Button btn_show_custom_button;
    /**
     * Notification 的ID
     */
    int notifyId = 101;
    /**
     * NotificationCompat 构造器
     */
    NotificationCompat.Builder mBuilder;
    /**
     * 是否在播放
     */
    public boolean isPlay = false;
    /**
     * 通知栏按钮广播
     */
    public ButtonBroadcastReceiver bReceiver;
    /**
     * 通知栏按钮点击事件对应的ACTION
     */
    public final static String ACTION_BUTTON = "com.notifications.intent.action.ButtonClick";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
        initView();
        initButtonReceiver();
    }

    private void initView() {
        btn_show_custom = (Button) findViewById(R.id.btn_show_custom);
        btn_show_custom.setOnClickListener(this);
        btn_show_custom_button = (Button) findViewById(R.id.btn_show_custom_button);
        btn_show_custom_button.setOnClickListener(this);
    }
    public void showBigView(){
        RemoteViews smallView = new RemoteViews(getPackageName(), R.layout.view_custom);
        smallView.setTextViewText(R.id.tv_custom_title, "aa");
        smallView.setImageViewResource(R.id.custom_icon, R.drawable.icon);

        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_launcher)
                .setNumber((int) (Math.random() * 1000))
                 .setTicker("you got a new message")
                .setDefaults(Notification.DEFAULT_SOUND
                        | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS)
                 .setAutoCancel(true)
                .setWhen(0)
                .setPriority(NotificationCompat.PRIORITY_LOW);

//在5.0版本之后，可以支持在锁屏界面显示notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            mBuilder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        }
        Notification   notification = mBuilder.build();
        notification.contentView = smallView;

//如果系统版本 >= Android 4.1，设置大视图 RemoteViews
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            RemoteViews view = new RemoteViews(getPackageName(), R.layout.view_custom);
            view.setTextViewText(R.id.tv_custom_content, "我是名字1我是名字2我是名字3我是名字4我是名字5我是名字6我是名字7我是名字");
            notification.bigContentView = view;
        }
        NotificationManager   notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
       notificationManager.notify(12121, notification);
    }

    public void showInboxStyle(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("InboxStyle");
        builder.setContentText("InboxStyle演示示例");
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon));
        android.support.v4.app.NotificationCompat.InboxStyle style = new android.support.v4.app.NotificationCompat.InboxStyle();
        style.setBigContentTitle("BigContentTitle")
                .addLine("第一行，第一行，第一行，第一行，第一行，第一行，第一行")
                .addLine("第二行")
                .addLine("第三行")
                .addLine("第四行")
                .addLine("第五行")
                .setSummaryText("SummaryText");
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this,CustomActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(id, notification);
    }
    public void shwoNotify() {
        //先设定RemoteViews
        RemoteViews view_custom = new RemoteViews(getPackageName(), R.layout.view_custom);
        view_custom.setImageViewResource(R.id.custom_icon, R.drawable.icon);
        view_custom.setTextViewText(R.id.tv_custom_title, "今日头条");
        view_custom.setTextViewText(R.id.tv_custom_content, "金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果。");
        //设置显示
        view_custom.setViewVisibility(R.id.tv_custom_time, View.VISIBLE);
        view_custom.setTextViewText(R.id.tv_custom_time, System.currentTimeMillis() + "");

        mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContent(view_custom)
                .setContentIntent(getDefalutIntent(Notification.FLAG_AUTO_CANCEL))
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("有新资讯")
                .setPriority(Notification.PRIORITY_HIGH)// 设置该通知优先级,浮动显示
                .setVibrate(new long[]{2000})
                .setOngoing(false)//不是正在进行的   true为正在进行  效果和.flag一样
                .setSmallIcon(R.drawable.icon);

        Notification notify = mBuilder.build();
        mNotificationManager.notify(notifyId, notify);
    }

    /**
     * 带按钮的通知栏
     */
    public void showButtonNotify() {
        NotificationCompat.Builder mBuilder = new Builder(this);
        RemoteViews mRemoteViews = new RemoteViews(getPackageName(), R.layout.view_custom_button);
        mRemoteViews.setImageViewResource(R.id.custom_song_icon, R.drawable.sing_icon);
        mRemoteViews.setTextViewText(R.id.tv_custom_song_singer, "周杰伦");
        mRemoteViews.setTextViewText(R.id.tv_custom_song_name, "七里香");
        mRemoteViews.setViewVisibility(R.id.ll_custom_button, View.VISIBLE);
        if (isPlay) {
            mRemoteViews.setImageViewResource(R.id.btn_custom_play, R.drawable.btn_pause);
        } else {
            mRemoteViews.setImageViewResource(R.id.btn_custom_play, R.drawable.btn_play);
        }

        //点击的事件处理
        Intent buttonIntent = new Intent(ACTION_BUTTON);
        /* 上一首按钮 */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PREV_ID);
        //这里加了广播，所及INTENT的必须用getBroadcast方法
        PendingIntent intent_prev = PendingIntent.getBroadcast(this, 1, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.btn_custom_prev, intent_prev);
		/* 播放/暂停  按钮 */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_PALY_ID);
        PendingIntent intent_paly = PendingIntent.getBroadcast(this, 2, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.btn_custom_play, intent_paly);
		/* 下一首 按钮  */
        buttonIntent.putExtra(INTENT_BUTTONID_TAG, BUTTON_NEXT_ID);
        PendingIntent intent_next = PendingIntent.getBroadcast(this, 3, buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mRemoteViews.setOnClickPendingIntent(R.id.btn_custom_next, intent_next);

        mBuilder.setContent(mRemoteViews)
                .setContentIntent(getDefalutIntent(Notification.FLAG_ONGOING_EVENT))
                .setWhen(System.currentTimeMillis())// 通知产生的时间，会在通知信息里显示
                .setTicker("正在播放")
                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                .setOngoing(true)
                .setSmallIcon(R.drawable.sing_icon);
        Notification notify = mBuilder.build();
        notify.flags = Notification.FLAG_ONGOING_EVENT;
        mNotificationManager.notify(200, notify);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_custom:
                shwoNotify();
               // showBigView();
              //  showInboxStyle();
                break;
            case R.id.btn_show_custom_button:
                showButtonNotify();
                break;
            default:
                break;
        }
    }

    /**
     * 带按钮的通知栏点击广播接收
     */
    public void initButtonReceiver() {
        bReceiver = new ButtonBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_BUTTON);
        registerReceiver(bReceiver, intentFilter);
    }

    public final static String INTENT_BUTTONID_TAG = "ButtonId";
    /**
     * 上一首 按钮点击 ID
     */
    public final static int BUTTON_PREV_ID = 1;
    /**
     * 播放/暂停 按钮点击 ID
     */
    public final static int BUTTON_PALY_ID = 2;
    /**
     * 下一首 按钮点击 ID
     */
    public final static int BUTTON_NEXT_ID = 3;

    /**
     * 广播监听按钮点击时间
     */
    public class ButtonBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ACTION_BUTTON)) {
                //通过传递过来的ID判断按钮点击属性或者通过getResultCode()获得相应点击事件
                int buttonId = intent.getIntExtra(INTENT_BUTTONID_TAG, 0);
                switch (buttonId) {
                    case BUTTON_PREV_ID:
                        Log.d(TAG, "上一首");
                        Toast.makeText(getApplicationContext(), "上一首", Toast.LENGTH_SHORT).show();
                        break;
                    case BUTTON_PALY_ID:
                        String play_status = "";
                        isPlay = !isPlay;
                        if (isPlay) {
                            play_status = "开始播放";
                        } else {
                            play_status = "已暂停";
                        }
                        showButtonNotify();
                        Log.d(TAG, play_status);
                        Toast.makeText(getApplicationContext(), play_status, Toast.LENGTH_SHORT).show();
                        break;
                    case BUTTON_NEXT_ID:
                        Log.d(TAG, "下一首");
                        Toast.makeText(getApplicationContext(), "下一首", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (bReceiver != null) {
            unregisterReceiver(bReceiver);
        }
        super.onDestroy();
    }
}
