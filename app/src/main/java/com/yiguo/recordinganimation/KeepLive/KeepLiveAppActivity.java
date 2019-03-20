package com.yiguo.recordinganimation.KeepLive;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yiguo.recordinganimation.KeepLive.service.RemoteService;
import com.yiguo.recordinganimation.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class KeepLiveAppActivity extends AppCompatActivity {

    public static boolean musicplay = false;
    class demoGet {
        private String token;
        public String getToken() throws ExecutionException, InterruptedException, TimeoutException {
            //需要等待token的返回,需要阻塞等待
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(8000);//异步请求token
                    token = "123";
                    System.out.println("Callable1.get"+token);
                    return token;
                }
            };
            ExecutorService threadPool = Executors.newFixedThreadPool(1);
            FutureTask task = new FutureTask<String>(callable);
            threadPool.submit(task);
            threadPool.execute(task);
            String token;
            token = (String) task.get(10, TimeUnit.SECONDS);//设置超时2秒
            System.out.println("Callable2.return"+token);
            return token;

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_live_app);
    }

    public void pxKeepLive(View view) {

    }

    public void AIDLKeepLive(View view) {
        startService(new Intent(KeepLiveAppActivity.this,  RemoteService.class));
    }

    public void MusicKeepLive(View view) {
        musicplay = true;
    }

    public void jobKeepLive(View view) {
        if (isServiceRunning(getApplicationContext(), getPackageName() + ":remote")) {
            Log.d("keepLive", "remote服务存活 ");
        } else {
            Log.d("keepLive", "remote服务被回收 ");
        }
    }

    public void notifyKeepLive(View view) {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("userType", "1");
        hashMap.put("userId", "232");
        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("topicId", "123");
            jsonArray.put(jsonObject);
            hashMap.put("sub", jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

//         demoGet demoGet = new  demoGet();
//        String token = null;
//        try {
//            token = demoGet.getToken();
//            System.out.println("Callable3.end"+token);
//            if (token == null) {
//                System.out.println("Callable3.timeOut,重新请求token");
//            }
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            // throw new RuntimeException("请求token超时");
//            System.out.println(e.getMessage());
//        }
//        System.out.println("Callable4.end"+token);
    }

    private boolean isServiceRunning(Context ctx, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) ctx
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> servicesList = activityManager
                .getRunningServices(Integer.MAX_VALUE);
        Iterator<ActivityManager.RunningServiceInfo> l = servicesList.iterator();
        while (l.hasNext()) {
            ActivityManager.RunningServiceInfo si = l.next();
            if (className.equals(si.process)) {
                isRunning = true;
            }
        }
        return isRunning;
    }
}
