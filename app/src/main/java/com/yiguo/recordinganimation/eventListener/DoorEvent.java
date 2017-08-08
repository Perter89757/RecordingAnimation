package com.yiguo.recordinganimation.eventListener;

import java.util.EventObject;

/**
 * author: huang_yanhui
 * data:2017/7/18
 * time:16:47
 * emaill:huangyh@thinkive.com
 * description:
 */

public class DoorEvent extends EventObject {

    private String doorState = "";// 表示门的状态，有“开”和“关”两种

    public DoorEvent(Object source, String doorState) {
        super(source);
        this.doorState = doorState;
    }

    public void setDoorState(String doorState) {
        this.doorState = doorState;
    }

    public String getDoorState() {
        return this.doorState;
    }
}
