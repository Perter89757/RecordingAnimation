package com.yiguo.recordinganimation.eventListener;

import java.util.EventListener;

/**
 * author: huang_yanhui
 * data:2017/7/18
 * time:16:48
 * emaill:huangyh@thinkive.com
 * description:
 */

public interface DoorListener extends EventListener {
    public void doorEvent(DoorEvent event);
}
