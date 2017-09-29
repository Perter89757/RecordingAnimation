package com.yiguo.recordinganimation.GlideLoad;

import android.os.Handler;

import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.stream.StreamModelLoader;

import java.io.InputStream;

/**
 * author: huang_yanhui
 * data:2017/9/28
 * time:17:04
 * emaill:huangyh@thinkive.com
 * description:
 */

public class ProgressModelLoader implements StreamModelLoader<String> {

    private final Handler handler;

    public ProgressModelLoader(Handler handler) {
        this.handler = handler;
    }
    @Override
    public DataFetcher<InputStream> getResourceFetcher(String model, int width, int height) {
        return  new ProgressDataFetcher(model, handler);
    }
}
