package com.yiguo.recordinganimation.KeepLive.service;

import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

/**
 * 定时器
 * 安卓5.0及以上
 */
@SuppressWarnings(value={"unchecked", "deprecation"})
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public final class JobHandlerService extends JobService {
    private JobScheduler mJobScheduler;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("keepLive","JobHandlerService_onStartCommand");
        //启动本地服务
        startService(new Intent(this, LocalService.class));
        //启动守护进程
        startService(new Intent(this, RemoteService.class));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mJobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder builder = new JobInfo.Builder(startId++,
                    new ComponentName(getPackageName(), JobHandlerService.class.getName()));
            if (Build.VERSION.SDK_INT >= 24) {
                builder.setMinimumLatency(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS); //执行的最小延迟时间
                builder.setOverrideDeadline(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);  //执行的最长延时时间
                builder.setMinimumLatency(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
                builder.setBackoffCriteria(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS, JobInfo.BACKOFF_POLICY_LINEAR);//线性重试方案
            } else {
                builder.setPeriodic(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);//设置时间间隔 该方法不能和setMinimumLatency、setOverrideDeadline这两个同时调用
            }
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);//有网络时执行
            builder.setRequiresCharging(true); // 当插入充电器，执行该任务
            mJobScheduler.schedule(builder.build());
        }
        return START_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        Log.d("keepLive","onStartJob_开始执行 ");
        if ( !isServiceRunning(getApplicationContext(), getPackageName()+":remote")) {
            startService(new Intent(this, LocalService.class));
            startService(new Intent(this, RemoteService.class));
            Log.d("keepLive","onStartJob_检测到远程停止_重启 ");
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d("keepLive","onStopJob");
        if (!isServiceRunning(getApplicationContext(), getPackageName()+":remote")) {
            startService(new Intent(this, LocalService.class));
            startService(new Intent(this, RemoteService.class));
            Log.d("keepLive","onStopJob_检测到远程停止_重启");
        }
        return false;
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
                Log.d("keepLive","远程服务存活 ");
            }
        }
        return isRunning;
    }
}
