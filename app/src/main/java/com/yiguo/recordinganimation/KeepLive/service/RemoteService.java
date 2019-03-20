package com.yiguo.recordinganimation.KeepLive.service;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.util.Log;

import com.fanjun.keeplive.service.GuardAidl;
import com.yiguo.recordinganimation.KeepLive.config.NotificationUtils;
import com.yiguo.recordinganimation.KeepLive.receiver.NotificationClickReceiver;
import com.yiguo.recordinganimation.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 远程服务
 */
@SuppressWarnings(value = {"unchecked", "deprecation"})
public final class RemoteService extends Service {
    private MyBilder mBilder;
    private MediaPlayer mediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();
        if (mBilder == null) {
            mBilder = new MyBilder();
        }
        Log.d("keepLive", "RemoteService_onCreate");

        //空通知栏 前台service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && Build.MANUFACTURER.toLowerCase().equals("xiaomi")) {
            Notification notification = new Notification.Builder(getApplicationContext(), "keepLive").build();
            // startForeground(11, notification);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBilder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            this.bindService(new Intent(RemoteService.this, LocalService.class), connection, Context.BIND_ABOVE_CLIENT);
            Log.d("keepLive", "----PushRemoteService-onStartCommand_bindService启动本地service");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.e("keepLive", "隔10秒执行一次操作,service存活");
                }
            }, 1000, 10000);

            //播放无声音乐
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.novioce);
                mediaPlayer.setVolume(0f, 0f);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        play();
                    }
                });
                play();
            }
        } catch (Exception e) {
        }
        return START_STICKY;
    }

    private void play() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            Log.d("keepLive", "LocalServiceon_play");
        } else {
            Log.d("keepLive", "禁止后台播放音乐");
        }
    }

    private void stopPlayMusic() {
        if (mediaPlayer != null) {
            Log.d("keepLive", "关闭后台播放音乐");
            mediaPlayer.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("keepLive", "----PushRemoteService-onDestroy-----");
        unbindService(connection);
        stopPlayMusic();

    }

    private final class MyBilder extends GuardAidl.Stub {

        @Override
        public void wakeUp(String title, String discription, int iconRes) throws RemoteException {
            Intent intent2 = new Intent(getApplicationContext(), NotificationClickReceiver.class);
            intent2.setAction(NotificationClickReceiver.CLICK_NOTIFICATION);
            Notification notification = NotificationUtils.createNotification(RemoteService.this, title, discription, iconRes, intent2);
            RemoteService.this.startForeground(13691, notification);
            Log.d("keepLive", "RemoteService_AIDL通信_wakeUp_显示通知");
        }

    }

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("keepLive", "----RemoteService-onServiceDisconnected");
            Intent remoteService = new Intent(RemoteService.this, LocalService.class);
            RemoteService.this.startService(remoteService);
            RemoteService.this.bindService(new Intent(RemoteService.this, LocalService.class), connection, Context.BIND_ABOVE_CLIENT);
            PowerManager pm = (PowerManager) RemoteService.this.getSystemService(Context.POWER_SERVICE);
            boolean isScreenOn = pm.isScreenOn();
            if (isScreenOn) {
                sendBroadcast(new Intent("_ACTION_SCREEN_ON"));
            } else {
                sendBroadcast(new Intent("_ACTION_SCREEN_OFF"));
            }
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("keepLive", "---远程和本地-通信正常");

        }
    };

}
