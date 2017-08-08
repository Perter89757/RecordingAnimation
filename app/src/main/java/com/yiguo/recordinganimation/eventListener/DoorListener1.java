package com.yiguo.recordinganimation.eventListener;

/**
 * author: huang_yanhui
 * data:2017/7/18
 * time:16:49
 * emaill:huangyh@thinkive.com
 * description:
 */

public class DoorListener1 implements DoorListener {
    @Override
    public void doorEvent(DoorEvent event) {
        if (event != null && event.getDoorState().equals("open")){
            System.out.println("门1打开");
        }else {
            System.out.println("门1关上");

        }
    }
}
