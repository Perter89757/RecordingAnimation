package com.yiguo.recordinganimation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import zhihudaily.thinkive.com.service_aidl.IServiceAidlInterface;
import zhihudaily.thinkive.com.service_aidl.IndependentService;
import zhihudaily.thinkive.com.service_aidl.bean.User;

public class AIDLActivity extends AppCompatActivity {

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        mText = (TextView) findViewById(R.id.txt_str);

        bindService();
    }

    private void bindService() {
        // UnBind
        unBindService();

        Intent intent = new Intent(this, IndependentService.class);
          bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }
    private void unBindService() {
        IServiceAidlInterface service = mService;
        mService = null;
        if (service != null) {
            unbindService(mConn);
        }
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

    private void showText(String str) {
        mText.append("\n");
        mText.append(str);
    }

    private void run() {
        User user = null;
        try {
            user = mService.getUser();
            showText(user.toString());

            mService.addAge();
            user = mService.getUser();
            showText(user.toString());

            mService.setName("FangFang");
            user = mService.getUser();
            showText(user.toString());

        } catch (RemoteException e) {
            showText(e.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindService();
    }



}
