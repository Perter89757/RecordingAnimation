package com.yiguo.recordinganimation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yiguo.recordinganimation.Fragment.DemoFragmentActivity;
import com.yiguo.recordinganimation.GlideLoad.GlideActivity;
import com.yiguo.recordinganimation.KeepLive.KeepLiveActivity;
import com.yiguo.recordinganimation.SQL.SQLActivity;
import com.yiguo.recordinganimation.Service.ServiceActivity;
import com.yiguo.recordinganimation.Temp.EmojiActivity;
import com.yiguo.recordinganimation.Temp.OnepxReceiver;
import com.yiguo.recordinganimation.Temp.TimeOutActivity;
import com.yiguo.recordinganimation.UI.AutoLayoutActivity;
import com.yiguo.recordinganimation.UI.JSONActivity;
import com.yiguo.recordinganimation.UI.UiActivity;
import com.yiguo.recordinganimation.View.ChenjinActivity;
import com.yiguo.recordinganimation.View.ViewActivity;
import com.yiguo.recordinganimation.callback.CallBackActivity;
import com.yiguo.recordinganimation.dagger.DaggerActivity;
import com.yiguo.recordinganimation.dagger.Student;
import com.yiguo.recordinganimation.eventBus.EventBusActivity;
import com.yiguo.recordinganimation.eventBus.MessageEvent;
import com.yiguo.recordinganimation.memoryLeake.LeakeActivity;
import com.yiguo.recordinganimation.mqtt.MQTTActivity;
import com.yiguo.recordinganimation.popwindows.PopWindowActivity;
import com.yiguo.recordinganimation.proxy.StudentHandler;
import com.yiguo.recordinganimation.proxy.studentInfo;

import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    public static void main(String[] s) {
        studentInfo studentInfo = (studentInfo) Proxy.newProxyInstance(com.yiguo.recordinganimation.proxy.Student.class.getClassLoader(), new Class[]{studentInfo.class}, new StudentHandler());
        studentInfo.smoke();
        try {
            //反射 设置变量的值
            Class<?> aClass = Class.forName("com.yiguo.recordinganimation.dagger.Student");
            Object newInstance = aClass.newInstance();
            Method showMethod = aClass.getDeclaredMethod("show", String.class);
            showMethod.setAccessible(true);
            showMethod.invoke(newInstance, "马大哈");

            Field name = aClass.getDeclaredField("name");
            name.set(newInstance, "Thinkive");
            Student student = (Student) newInstance;
            System.out.print("发射变量赋值:" + student.name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button26).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LeakeActivity.class));

            }
        });

        findViewById(R.id.button25).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, JSONActivity.class));

            }
        });
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
                @SuppressLint("ResourceType")
                InputStream is = getResources().openRawResource(R.drawable.bj);
                Bitmap mBitmap = BitmapFactory.decodeStream(is);
                intent.putExtra("bitmap", mBitmap);
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
        });
        findViewById(R.id.button23).setOnClickListener(new View.OnClickListener() {
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
