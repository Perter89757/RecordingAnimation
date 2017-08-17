package com.yiguo.recordinganimation.Service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.yiguo.recordinganimation.R;
import com.yiguo.recordinganimation.callback.CallBack;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startService;

    private Button stopService;

    private Button bindService;

    private Button unbindService;
    private PushServiceImpl pushService;
    private Button login;
    private Button stop_service;
    private Button unbind_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Intent intent = new Intent(this, PushService.class);
        startActivity(intent);
        login = (Button) findViewById(R.id.login);
        stop_service = (Button) findViewById(R.id.stop_service);
        unbind_service = (Button) findViewById(R.id.unbind_service);

        login.setOnClickListener(this);
        stop_service.setOnClickListener(this);
        unbind_service.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, PushService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
        PushServiceProxy pushServiceImp = new PushServiceProxy(pushService.messenger_Service);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            case R.id.login:
                pushService.login(12345, new CallBack() {
                    @Override
                    public void onsuceess() {

                    }

                    @Override
                    public void onerror(String stirng) {
                        Log.d("TAG","activity回调的数据:"+stirng);
                    }
                });
                break;

        }
    }

    private ServiceConnection connection = new ServiceConnection() {

        @Override//服务断开
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override//服务连接,调用服务的方法
        public void onServiceConnected(ComponentName name, IBinder service) {
            pushService = (PushServiceImpl) (service);
        }
    };
}
