package com.yiguo.recordinganimation.Service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yiguo.recordinganimation.R;

import java.util.Random;

import zhihudaily.thinkive.com.service_aidl.IServiceAidlInterface;
import zhihudaily.thinkive.com.service_aidl.IndependentService;
import zhihudaily.thinkive.com.service_aidl.NotifyCallBack;
import zhihudaily.thinkive.com.service_aidl.RemoteCallBackService;
import zhihudaily.thinkive.com.service_aidl.RestaurantAidlInterface;
import zhihudaily.thinkive.com.service_aidl.bean.User;

public class AIDLActivity extends AppCompatActivity {

    private TextView mText;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        mText = (TextView) findViewById(R.id.txt_str);
        result = (TextView) findViewById(R.id.result);
        Button add = (Button) findViewById(R.id.add);
        Button leave = (Button) findViewById(R.id.leave);

        bindService();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mService1.join(new Binder(), getRandomString(6));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // mService.registerCallBack(mNotifyCallBack);
                    mService1.leave();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindService() {
        // UnBind
        unBindService();

        Intent intent = new Intent(this, IndependentService.class);
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);

        Intent intent1 = new Intent(this, RemoteCallBackService.class);
        bindService(intent1,mConn1,BIND_AUTO_CREATE);
    }

    private void unBindService() {
        IServiceAidlInterface service = mService;
        mService = null;
        if (service != null) {
            unbindService(mConn);
        }
    }
    private RestaurantAidlInterface mService1;
    private ServiceConnection mConn1 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService1 = RestaurantAidlInterface.Stub.asInterface(service);
            try {
                //我们这个demo里面 只注册了一个回调 实际上可以注册很多个回调 因为service里面 我们存的是list callback
                mService1.registerCallBack(mNotifyCallBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            try {
                mService1.unregisterCallBack(mNotifyCallBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mService1 = null;
        }
    };
    private NotifyCallBack mNotifyCallBack = new NotifyCallBack.Stub() {

        @Override
        public void notifyMainUiThread(String name, boolean joinOrLeave,int count) throws RemoteException {
            String toastStr = "";
            if (joinOrLeave) {
                toastStr = name + "进入了餐厅";
            } else {
                toastStr = name + "离开了餐厅";
            }
            result.setText(toastStr+"还有"+count+"人");
        }
    };
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }















    private IServiceAidlInterface mService;
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = IServiceAidlInterface.Stub.asInterface(iBinder);
            if (mService != null)
                run();
            else
                showText("Bind Error.");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mService = null;
        }
    };


    private void run() {
        User user = null;
        try {
            user = mService.getUser();
            showText("获得service的数据:" + user.toString());

            mService.addAge();
            user = mService.getUser();
            showText("activity修改servie的age:" + user.toString());

            mService.setName("FangFang");
            user = mService.getUser();
            showText("activity修改name:" + user.toString());

        } catch (RemoteException e) {
            showText(e.toString());
        }
    }

    private void showText(String str) {
        mText.append("\n");
        mText.append("\n");
        mText.append(str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindService();
    }


}
