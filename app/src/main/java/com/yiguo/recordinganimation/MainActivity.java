package com.yiguo.recordinganimation;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yiguo.recordinganimation.Switch.BarActivity;
import com.yiguo.recordinganimation.Switch.SwitchActivity;
import com.yiguo.recordinganimation.Switch.ViewActivity;
import com.yiguo.recordinganimation.exception.NoLoginNameException;
import com.yiguo.recordinganimation.popwindows.PopWindowActivity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import static com.yiguo.recordinganimation.LogUtils.getLogDirectory;


public class MainActivity extends AppCompatActivity {


    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private NetChangeReceiver netChangeReceiver;
    private BroadcastReceiver receiver;

    public static void main(String[] s) {
        String s1 = "/storage/emulated/0/储宝宝/cach/7426dc40-a03b-4f54-b9a6-8bfc0cbc2c4c.mp4/B1C42F68-13CB-4B21-8761-E912C3AA2387";
        String s11 = "7426dc40-a03b-4f54-b9a6-8bfc0cbc2c4c.mp4";
        String s2 = "123";
        String localPath = s1;
        int separator = localPath.lastIndexOf(File.separator);
        int separator1 = localPath.lastIndexOf(".");
        int separator12 = localPath.lastIndexOf("/");
//        System.out.println("位置: " + separator);
//        System.out.println("位置:\"/\" " + separator1);
//        if (separator1 != -1) {
//            String local = localPath.substring(0, separator1);
//            String local2 = localPath.substring(separator12 + 1, localPath.length());
//
//            System.out.println("local: " + local);
//            System.out.println("local2: " + local2 + ".mp4");
//        }


        final Thread thread = new Thread(new Runnable() {

            public int total;

            @Override
            public void run() {
                synchronized (this) {
                    System.out.println("ThreadB is running..");
                    for (int i = 0; i < 3; i++) {
                        total += i;
                        try {
                            wait(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("total is " + total);
                    }
                    notify();
                }
            }
        });

        thread.start();
        System.out.println("1.b线程.start ");
        synchronized (thread) {
            System.out.println("2.b线程等待");
            try {
                thread.wait();
                System.out.println("b线程等待结束,运行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("b线程运行结束");

    }

    private RecordView mRecorfView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int db = (int) (Math.random() * 100);
            mRecorfView.setVolume(db);
        }
    };
    private int nowModel = RecordView.MODEL_RECORD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TimeOutActivity.class));
                finish();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("出错了!").setMessage("coockie").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserViewActivity.class));

            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ListenerActivity.class));

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
//                try {
//                    Intent intent = MainActivity.this.getPackageManager().getLaunchIntentForPackage("com.thinkive.im.huataipush");
//
//                    startActivity(intent);
//                } catch (Exception e) {
//                    Toast.makeText(MainActivity.this, "没有安装", Toast.LENGTH_LONG).show();
//                }
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED)
                {

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);
                } else
                {
                    callPhone();
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
        findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SwitchActivity.class));
            }
        });
        findViewById(R.id.button12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewActivity.class));
            }
        });
        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BarActivity.class));
            }
        });


        //反射调用
//        Class<Test> testClass = Test.class;
//        try {
//            Method privateMethod = testClass.getDeclaredMethod("privateMethod", null);
//            privateMethod.setAccessible(true);
//            privateMethod.invoke(new Test(), null);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }


        getAppCache(MainActivity.this);
        File tempdir = MainActivity.this.getExternalFilesDir(null);
        String s = getLogDirectory() + File.separator;
        Log.d("TAG", s);


        netChangeReceiver = new NetChangeReceiver();
        registerReceiver(netChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        try {
            login("da");
        } catch (NoLoginNameException e) {
            e.printStackTrace();
        }

        OnepxReceiver.register1pxReceiver(this);
    }


    public void login(String name) throws NoLoginNameException {

    }

    public void callPhone()
    {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhone();
            } else
            {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public class NetChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //接受广播触发刷新,使用了系统服务获取到了网络变化
            Log.d("TAG", "触发了广播");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 215) {
            String data1 = data.getStringExtra("data");
            Toast.makeText(this, "返回数据" + data1, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ViewActivity.class));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (netChangeReceiver != null) {
            unregisterReceiver(netChangeReceiver);
        }
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
    }

    private TimerTask timeTask;
    private Timer timeTimer = new Timer(true);


    /**
     * 获取应用程序缓存文件夹下的指定目录
     *
     * @param context
     * @return
     */
    public static String getAppCache(Context context) {
        String savePath = context.getCacheDir().getAbsolutePath();
        File savedir = new File(savePath);
        if (!savedir.exists()) {
            savedir.mkdirs();
        }
        Log.d("TAG", savePath);
        return savePath;
    }
}
