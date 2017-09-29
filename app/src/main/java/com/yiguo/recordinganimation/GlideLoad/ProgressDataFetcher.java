package com.yiguo.recordinganimation.GlideLoad;

import android.os.Handler;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;

import java.io.InputStream;

/**
 * author: huang_yanhui
 * data:2017/9/28
 * time:17:29
 * emaill:huangyh@thinkive.com
 * description:
 */

class ProgressDataFetcher implements DataFetcher<InputStream> {
    private final Handler handler;
    private String url;

    public ProgressDataFetcher(String url, Handler handler) {
        this.url = url;
        this.handler = handler;
    }

    @Override
    public InputStream loadData(Priority priority) throws Exception {

        return null;
    }

    @Override
    public void cleanup() {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void cancel() {

    }
}
