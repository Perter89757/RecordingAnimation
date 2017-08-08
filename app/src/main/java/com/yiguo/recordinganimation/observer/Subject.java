package com.yiguo.recordinganimation.observer;

/**
 * author: huang_yanhui
 * data:2017/7/31
 * time:10:42
 * emaill:huangyh@thinkive.com
 * description:
 */

public interface Subject {
    void addObsever(observer observer);
    void delObasever(observer observer);
    void notifyObserver();
}
