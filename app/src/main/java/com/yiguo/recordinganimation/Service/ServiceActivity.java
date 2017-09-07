package com.yiguo.recordinganimation.Service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;

import com.yiguo.recordinganimation.AIDLActivity;
import com.yiguo.recordinganimation.MainActivity;
import com.yiguo.recordinganimation.R;
import com.yiguo.recordinganimation.Service.loadServiceData.CmdMessage;
import com.yiguo.recordinganimation.Service.loadServiceData.DouBanService;
import com.yiguo.recordinganimation.Service.loadServiceData.bean.DouBanMovie;
import com.yiguo.recordinganimation.callback.CallBack;
import com.yiguo.recordinganimation.utils.LogUtils;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button login;

    private PushServiceProxy pushServiceProxy;
    private Button loadServiceData;
    private static int tokenNumber;
    private static SparseArray tokenMap = new SparseArray();
    private Button dianCan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_service);
        Intent intent = new Intent(this, PushService.class);
        startService(intent);
        tkCallBack.registerBackReceiver(this);

        login = (Button) findViewById(R.id.login);
        loadServiceData = (Button) findViewById(R.id.loadServiceData);
        dianCan = (Button) findViewById(R.id.dianCan);
        login.setOnClickListener(this);
        loadServiceData.setOnClickListener(this);
        dianCan.setOnClickListener(this);

        startService(new Intent(ServiceActivity.this, DouBanService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, PushService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                pushServiceProxy.login(12345, new tkCallBack(new CallBack() {
                    @Override
                    public void onsuceess(Object o) {

                    }

                    @Override
                    public void onerror(String stirng) {
                        Log.d("TAG", "回调到activity:" + stirng);
                    }
                }));
                break;

            case R.id.dianCan:
                Intent intent = new Intent(ServiceActivity.this, AIDLActivity.class);
                startActivity(intent);
                break;

            case R.id.loadServiceData:
                RemoteCallBack remoteCallBack = new RemoteCallBack(new TokenCallBack() {
                    @Override
                    public void onsuceess(Object o) {
                        DouBanMovie douBanMovie = (DouBanMovie) o;
                        Log.d("DouBanService", "acitivy RemoteCallBack 收到数据---" + douBanMovie.toString());
                    }

                    @Override
                    public void onerror(String stirng) {

                    }
                });
                Log.d("DouBanService", "new CallBack---" + remoteCallBack);
                String token = storeToken(remoteCallBack);
                LoadDouBanData(3, 2, token,remoteCallBack );
                break;

        }
    }

    private void LoadDouBanData(int start,int count,String remoteCallBack_key,RemoteCallBack callBack) {
        CmdMessage remoteCmdMsg = new CmdMessage();
        remoteCmdMsg.setCount(start);
        remoteCmdMsg.setStart(count);
        Intent serviceIntent = new Intent(ServiceActivity.this, DouBanService.class);
        serviceIntent.putExtra("cmdMessage",  remoteCmdMsg);
        serviceIntent.putExtra("callback_key",remoteCallBack_key);
        startService(serviceIntent);
    }
    public static String storeToken(RemoteCallBack remoteCallBack) {
        tokenMap.put(tokenNumber, remoteCallBack);
        return Integer.toString(tokenNumber++);
    }
    private ServiceConnection connection = new ServiceConnection() {

        @Override//服务断开
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override//服务连接,调用服务的方法
        public void onServiceConnected(ComponentName name, IBinder service) {
            pushServiceProxy = new PushServiceProxy(service);

        }
    };

    public static void notifyRemoteCallBack(int token, DouBanMovie bean){
        RemoteCallBack callback = (RemoteCallBack) tokenMap.get(token);
        LogUtils.d("DouBanService", "主进程notifyRemoteCallBack,token = " + token + " , callback = "+ callback);
        callback.onsuceess(bean);
    }
}
