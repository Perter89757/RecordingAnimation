package com.yiguo.recordinganimation.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * author: huang_yanhui
 * data:2017/7/31
 * time:10:47
 * emaill:huangyh@thinkive.com
 * description:
 */

public class SubscriptionSubject implements Subject {
    private List<observer> observerList = new ArrayList<>();

    @Override
    public void addObsever(observer observer) {
        if (observerList == null) {
            observerList = new ArrayList<>();
        }
        observerList.add(observer);
    }

    @Override
    public void delObasever(observer observer) {
        //
        if (observerList != null) {
            observerList.remove(observer);
        }
    }

    @Override
    public void notifyObserver() {
        for (observer observer : observerList) {
            observer.updata();
        }
    }
}
