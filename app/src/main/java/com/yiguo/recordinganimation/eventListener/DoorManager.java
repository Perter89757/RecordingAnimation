package com.yiguo.recordinganimation.eventListener;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * author: huang_yanhui
 * data:2017/7/18
 * time:16:52
 * emaill:huangyh@thinkive.com
 * description:
 */

public class DoorManager {
    private Collection listeners;

    public void addListener(DoorListener listener){
        if (listeners == null){
            listeners = new HashSet();
        }
        listeners.add(listener);
    }

    public  void removeListener(DoorListener listener){
        if (listeners != null) {
            return;
        }
        listeners.remove(listener);
    }

    public  void notifyListener(DoorEvent event){
        Iterator iter = listeners.iterator();
        while (iter.hasNext()) {
            DoorListener listener = (DoorListener) iter.next();
            listener.doorEvent(event);
        }
    }

    public void open( ){
        if (listeners == null) {
            return;
        }
        DoorEvent doorEvent = new DoorEvent(this,"open");
        notifyListener(doorEvent);
    }

    public void close( ){
        if (listeners == null) {
            return;
        }
        DoorEvent doorEvent = new DoorEvent(this,"close");
        notifyListener(doorEvent);
    }

}
