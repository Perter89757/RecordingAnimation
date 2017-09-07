package com.yiguo.recordinganimation.Service.loadServiceData.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * author: huang_yanhui
 * data:2017/9/6
 * time:17:02
 * emaill:huangyh@thinkive.com
 * description:
 */

public class AvatarsBeanX implements Parcelable {
         /**
         * small : https://img3.doubanio.com/img/celebrity/small/230.jpg
         * large : https://img3.doubanio.com/img/celebrity/large/230.jpg
         * medium : https://img3.doubanio.com/img/celebrity/medium/230.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.small);
        dest.writeString(this.large);
        dest.writeString(this.medium);
    }

    public AvatarsBeanX() {
    }

    protected AvatarsBeanX(Parcel in) {
        this.small = in.readString();
        this.large = in.readString();
        this.medium = in.readString();
    }

    public static final Parcelable.Creator<AvatarsBeanX> CREATOR = new Parcelable.Creator<AvatarsBeanX>() {
        @Override
        public AvatarsBeanX createFromParcel(Parcel source) {
            return new AvatarsBeanX(source);
        }

        @Override
        public AvatarsBeanX[] newArray(int size) {
            return new AvatarsBeanX[size];
        }
    };
}
