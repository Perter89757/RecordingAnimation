package com.yiguo.recordinganimation.utils.imageDemo;

/**
 * Created by huang_yanhui on 2018/6/14.
 */

public class imageLoadUtils {
    private static imageLoadUtils sInstance;
    private BaseImageLoad loadFrame;

    private imageLoadUtils() {
    }
    //单例模式
    public static imageLoadUtils getInstance() {
        if (sInstance == null) {
            synchronized (imageLoadUtils.class) {
                if (sInstance == null) {
                    //若切换其它图片加载框架，可以实现一键替换
                    sInstance = new imageLoadUtils();
                }
            }
        }
        return sInstance;
    }

    //设置具体的额图片加载框架
    public void setImageLoadFrame(BaseImageLoad imageLoad) {
        loadFrame = imageLoad;
    }

    public imageLoadOptions loadurl(String url) {
        return new imageLoadOptions(url);
    }

    public void loadOptions(imageLoadOptions imageLoadOptions) {
        loadFrame.LoadOptions(imageLoadOptions);
    }

}
