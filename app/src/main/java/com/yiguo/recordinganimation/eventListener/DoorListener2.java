package com.yiguo.recordinganimation.eventListener;

/**
 * author: huang_yanhui
 * data:2017/7/18
 * time:16:50
 * emaill:huangyh@thinkive.com
 * description:
 */

public class DoorListener2 implements DoorListener {
    @Override
    public void doorEvent(DoorEvent event) {
        if (event != null && event.getDoorState().equals("open")){
            System.out.println("门2打开,开灯");
        }else {
            System.out.println("关灯,门2关上");
        }
    }
}
