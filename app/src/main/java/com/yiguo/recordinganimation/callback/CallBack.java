package com.yiguo.recordinganimation.callback;

/**
 * author: huang_yanhui
 * data:2017/6/13
 * time:16:40
 * emaill:huangyh@thinkive.com
 * description:
 */

public interface CallBack<T> {
    void onsuceess(T o);
    void onerror(String stirng);
}
