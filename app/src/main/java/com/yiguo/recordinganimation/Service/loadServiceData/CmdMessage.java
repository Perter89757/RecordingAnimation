package com.yiguo.recordinganimation.Service.loadServiceData;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author: huang_yanhui
 * data:2017/9/6
 * time:15:05
 * emaill:huangyh@thinkive.com
 * description:
 */

public class CmdMessage implements Parcelable {
    private int start ;
    private int count ;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.start);
        dest.writeInt(this.count);
    }

    public CmdMessage() {
    }

    protected CmdMessage(Parcel in) {
        this.start = in.readInt();
        this.count = in.readInt();
    }

    public static final Creator<CmdMessage> CREATOR = new Creator<CmdMessage>() {
        @Override
        public CmdMessage createFromParcel(Parcel source) {
            return new CmdMessage(source);
        }

        @Override
        public CmdMessage[] newArray(int size) {
            return new CmdMessage[size];
        }
    };

    @Override
    public String toString() {
        return "CmdMessage{" +
                "start=" + start +
                ", count=" + count +
                '}';
    }
}
