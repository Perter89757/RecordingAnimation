package com.yiguo.recordinganimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yiguo.recordinganimation.Fragment.DemoFragmentActivity;
import com.yiguo.recordinganimation.KeepLive.KeepLiveActivity;
import com.yiguo.recordinganimation.SQL.SQLActivity;
import com.yiguo.recordinganimation.Service.ServiceActivity;
import com.yiguo.recordinganimation.Temp.EmojiActivity;
import com.yiguo.recordinganimation.Temp.OnepxReceiver;
import com.yiguo.recordinganimation.Temp.TimeOutActivity;
import com.yiguo.recordinganimation.UI.AutoLayoutActivity;
import com.yiguo.recordinganimation.UI.GlideActivity;
import com.yiguo.recordinganimation.UI.UiActivity;
import com.yiguo.recordinganimation.View.ChenjinActivity;
import com.yiguo.recordinganimation.View.ViewActivity;
import com.yiguo.recordinganimation.callback.CallBackActivity;
import com.yiguo.recordinganimation.dagger.DaggerActivity;
import com.yiguo.recordinganimation.eventBus.EventBusActivity;
import com.yiguo.recordinganimation.eventBus.MessageEvent;
import com.yiguo.recordinganimation.mqtt.MQTTActivity;
import com.yiguo.recordinganimation.popwindows.PopWindowActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.atomic.AtomicInteger;


public class MainActivity extends AppCompatActivity {

    public static void main(String[] s) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AtomicInteger();
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
                Intent intent = new Intent(MainActivity.this, AutoLayoutActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button20).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().postSticky(new MessageEvent("粘性事件"));
                Intent intent = new Intent(MainActivity.this, EventBusActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GlideActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DaggerActivity.class);
                startActivity(intent);
            }
        });      findViewById(R.id.button23).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SQLActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.button24).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DemoFragmentActivity.class);
                startActivity(intent);
            }
        });


        OnepxReceiver.register1pxReceiver(this);

    }


    @Override
    protected void onStop() {

        super.onStop();
        //  EventBus.getDefault().post(new MessageEvent("Main发送一条消息"));//普通消息事件
    }
}
