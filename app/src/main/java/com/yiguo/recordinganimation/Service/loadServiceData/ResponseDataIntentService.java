package com.yiguo.recordinganimation.Service.loadServiceData;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.yiguo.recordinganimation.Service.ServiceActivity;
import com.yiguo.recordinganimation.Service.loadServiceData.bean.DouBanMovie;
import com.yiguo.recordinganimation.utils.LogUtils;

/**
 * author: huang_yanhui
 * data:2017/9/7
 * time:15:00
 * emaill:huangyh@thinkive.com
 * description:
 */

public class ResponseDataIntentService extends IntentService {

    public ResponseDataIntentService() {
        super("ResponseDataIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        LogUtils.d("DouBanService", "主进程的intnetservice , intent is:" + intent);
        if (null != intent) {
            DouBanMovie movieBean = intent.getParcelableExtra("new_message");
            String callback_key = intent.getStringExtra("callback_key");
            int token = Integer.valueOf(callback_key).intValue();
            ServiceActivity.notifyRemoteCallBack(token,movieBean);
        }
    }
}
