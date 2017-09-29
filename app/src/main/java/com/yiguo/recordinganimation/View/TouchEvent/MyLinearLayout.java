package com.yiguo.recordinganimation.View.TouchEvent;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * author: huang_yanhui
 * data:2017/9/29
 * time:15:04
 * emaill:huangyh@thinkive.com
 * description:
 */

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("event", "MyLinearLayout-dispatchTouchEvent");
       // return true;
          return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("event", "MyLinearLayout-onInterceptTouchEvent");
         //return true;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("event", "MyLinearLayout-onTouchEvent");
        //return super.onTouchEvent(event);
        return true;
    }
}
