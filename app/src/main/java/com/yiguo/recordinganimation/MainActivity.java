package com.yiguo.recordinganimation;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yiguo.recordinganimation.ActivityKiller.AppKillerActivity;
import com.yiguo.recordinganimation.KeepLive.KeepLiveActivity;
import com.yiguo.recordinganimation.Service.ServiceActivity;
import com.yiguo.recordinganimation.Temp.EmojiActivity;
import com.yiguo.recordinganimation.Temp.OnepxReceiver;
import com.yiguo.recordinganimation.Temp.TimeOutActivity;
import com.yiguo.recordinganimation.UI.UiActivity;
import com.yiguo.recordinganimation.View.ChenjinActivity;
import com.yiguo.recordinganimation.View.ViewActivity;
import com.yiguo.recordinganimation.callback.CallBackActivity;
import com.yiguo.recordinganimation.exception.AppStatusConstant;
import com.yiguo.recordinganimation.exception.AppStatusManager;
import com.yiguo.recordinganimation.mqtt.MQTTActivity;
import com.yiguo.recordinganimation.popwindows.PopWindowActivity;


public class MainActivity extends BaseActivity {

    public static void main(String[] s) {

    }

    @Override
    protected void restartApp() {
        super.restartApp();
        Toast.makeText(getApplicationContext(),"应用被回收重启",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,LauncherActivity.class));
        finish();
    }

    @Override
    protected void setUpViewAndData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AppStatusManager.getInstance().setAppStatus(AppStatusConstant.STATUS_NORMAL);//进入应用初始化设置成未登录状态

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UiActivity.class));

            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TimeOutActivity.class));
                finish();
            }
        });


        findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EmojiActivity.class));
            }
        });
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = MainActivity.this.getPackageManager().getLaunchIntentForPackage("com.thinkive.im.huataipush");
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "没有安装", Toast.LENGTH_LONG).show();
                }
            }
        });


        findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(MainActivity.this,CallBackActivity.class));
                startActivityForResult(new Intent(MainActivity.this, CallBackActivity.class), 214);
            }
        });
        findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PopWindowActivity.class));
            }
        });

        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewActivity.class));
            }
        });

        findViewById(R.id.button14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChenjinActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button17).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MQTTActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button18).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KeepLiveActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button19).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AppKillerActivity.class);
                startActivity(intent);
            }
        });

        OnepxReceiver.register1pxReceiver(this);

    }


}
