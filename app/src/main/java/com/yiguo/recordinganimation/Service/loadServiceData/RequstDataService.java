package com.yiguo.recordinganimation.Service.loadServiceData;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.yiguo.recordinganimation.Service.loadServiceData.bean.DouBanMovie;
import com.yiguo.recordinganimation.utils.LogUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author: huang_yanhui
 * data:2017/9/6
 * time:15:56
 * emaill:huangyh@thinkive.com
 * description:
 */

public class RequstDataService extends IntentService {

    private OkHttpClient mOkHttpClient;


    public RequstDataService( ) {
        super("RequstDataService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("DouBanService", "RequstDataService,onCreate" );

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        LogUtils.d("DouBanService", "onHandleIntent, intent is:" + intent);
        if(null != intent && intent.hasExtra("cmdMessage")){
            LogUtils.d("DouBanService", "onStartCommand, intent is:" + intent);
            CmdMessage cmdMessage = (CmdMessage)intent.getParcelableExtra("cmdMessage");
            String callback_key = intent.getStringExtra("callback_key");
            int count = cmdMessage.getCount();
            int start = cmdMessage.getStart();
            if (count > 0 && start > 0){
                getAsynHttp(start,count,callback_key);
            }else {
                LogUtils.d("DouBanIntentService", "请求参数不满足" + cmdMessage.toString());
            }
        }


    }


    private void getAsynHttp(int start, int count, final String callback_key) {
        mOkHttpClient =new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("https://api.douban.com/v2/movie/top250?start="+start+"&count="+count);
        Log.d("DouBanService", "network---" + "https://api.douban.com/v2/movie/top250?start="+start+"&count="+count);
        requestBuilder.method("GET",null);
        Request request = requestBuilder.build();
        Call mcall= mOkHttpClient.newCall(request);
        mcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("DouBanService", "network---error"  );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Gson gson = new Gson();
                DouBanMovie douBanMovie = gson.fromJson(json, DouBanMovie.class);
                String url = response.networkResponse().toString();
                Log.d("DouBanService", "network---" + url);
                Log.d("DouBanService", "数据---" + douBanMovie);
                Intent intentReceiver = new Intent();
                intentReceiver.setAction("com.yiguo.recordinganimation.douban.action.NEW_MESSAGE");
                intentReceiver.putExtra("new_message",douBanMovie);
                intentReceiver.putExtra("callback_key",callback_key);
                sendBroadcast(intentReceiver);
            }
        });
    }
}
