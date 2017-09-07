package com.yiguo.recordinganimation.Service;

import android.util.Log;

import com.yiguo.recordinganimation.callback.CallBack;

/**
 * author: huang_yanhui
 * data:2017/9/6
 * time:17:23
 * emaill:huangyh@thinkive.com
 * description:
 */

class RemoteCallBack<T> implements CallBack<T> {

    private TokenCallBack tokenCallBack;

    public RemoteCallBack(TokenCallBack tokenCallBack) {
        this.tokenCallBack = tokenCallBack;
    }

    @Override
    public void onsuceess(T t) {
        Log.d("DouBanService", "RemoteCallBack收到数据---");
        tokenCallBack.onsuceess(t);
    }


    @Override
    public void onerror(String stirng) {
        tokenCallBack.onerror(stirng);
    }
}
