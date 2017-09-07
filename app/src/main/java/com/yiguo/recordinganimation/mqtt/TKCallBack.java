package com.yiguo.recordinganimation.mqtt;

/**
 * author: huang_yanhui
 * data:2017/8/28
 * time:15:37
 * emaill:huangyh@thinkive.com
 * description:
 */

public interface TKCallBack {

    int ERROR_EXCEPTION = -1;
    int ERROR_CONNECT = -2;
    int ERROR_DISCONNECT = -3;
    int ERROR_SUBSCRIBE = -4;
    int ERROR_UNSUBSCRIBE = -5;
    int ERROR_PUBLISH = -6;
    int ERROR_REQUEST = -1101;
    int ERROR_DOWN = -1201;
    int ERROR_UPLOAD = -1301;

    void onSuccess();

    void onError(int var1, String var2);
}
