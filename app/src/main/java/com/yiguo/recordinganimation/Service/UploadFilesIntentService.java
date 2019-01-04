package com.yiguo.recordinganimation.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by huang_yanhui on 2018/12/14.
 */
public class UploadFilesIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
      */
    public UploadFilesIntentService( ) {
        super("UploadFilesIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("UploadFiles","开启上传文件");

    }

    public static void startActionFoo(Context context, String loadPath) {
        Intent intent = new Intent(context, UploadFilesIntentService.class);

//开启服务兼容
       // if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
           // context.startForegroundService(intent);
      //  } else {
            context.startService(intent);
     //   }
    }

}
