package com.yiguo.recordinganimation.observer;

/**
 * author: huang_yanhui
 * data:2017/7/31
 * time:10:50
 * emaill:huangyh@thinkive.com
 * description:
 */

public class Client {
    public static void main(String[] args) {
        WeiXinUser weiXinUser1 = new WeiXinUser("渣哥");
        WeiXinUser weiXinUser2 = new WeiXinUser("小强");
        SubscriptionSubject subject = new SubscriptionSubject();
        subject.addObsever(weiXinUser1);
        subject.addObsever(weiXinUser2);
        subject.notifyObserver();
    }
}
