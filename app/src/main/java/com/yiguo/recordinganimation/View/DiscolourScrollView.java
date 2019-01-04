package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * author: huang_yanhui
 * data:2017/8/23
 * time:16:38
 * emaill:huangyh@thinkive.com
 * description:
 * 重写了onScrollChanged()方法 监听xy值的变化
 */

public class DiscolourScrollView extends ScrollView {

    public DiscolourScrollView(Context context) {
        super(context);
    }

    public DiscolourScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DiscolourScrollView(Context context, AttributeSet attrs,int defStyle) {
        super(context, attrs, defStyle);
    }
    //接口回调
    private ScrollViewListener scrollViewListener;
    public interface ScrollViewListener {
        void onScrollChanged(DiscolourScrollView scrollView, int x, int y,int oldx, int oldy);
    }
    public void setScrollViewListener(ScrollViewListener  scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            //滑动时,xy值的变化
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
