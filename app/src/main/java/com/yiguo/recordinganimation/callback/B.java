package com.yiguo.recordinganimation.callback;

import android.util.Log;

/**
 * author: huang_yanhui
 * data:2017/6/13
 * time:16:41
 * emaill:huangyh@thinkive.com
 * description:
 */

public class B implements CallBack {
    public B() {

    }

    @Override
    public void onsuceess() {
        Log.d("CallBack","B实现了接口");
    }

    @Override
    public void onerror(String stirng) {

    }

    public void bfangfa(){
        onsuceess();
    }
}
