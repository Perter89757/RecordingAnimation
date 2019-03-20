package com.yiguo.recordinganimation.utils.imageDemo;

import android.widget.ImageView;

/**
 * Created by huang_yanhui on 2018/6/14.
 */

public class imageLoadOptions {
    public String url;
    public int errorPicID;

    public imageLoadOptions(String url) {
        this.url = url;
    }

    public imageLoadOptions errorPic(int errorPicID) {
        this.errorPicID = errorPicID;
        return this;
    }

    public void into(ImageView imageView) {
        imageLoadUtils.getInstance().loadOptions(this);
    }


}
