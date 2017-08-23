package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * author: huang_yanhui
 * data:2017/6/27
 * time:10:27
 * emaill:huangyh@thinkive.com
 * description:
 */

public class MyLayout extends LinearLayout {



    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int childCount = getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View childAt = getChildAt(i);
//            childAt.onTouchEvent()
//        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        //nativeGetRawAxisValue(mNativePtr, AXIS_X, 0, HISTORY_CURRENT);
        float rawX = ev.getRawX();
        // nativeGetAxisValue(mNativePtr, AXIS_X, 0, HISTORY_CURRENT);
         float down_x = 0;
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                //如果你觉得需要拦截
                down_x = ev.getX();
                return false ;
            case MotionEvent.ACTION_MOVE:
                //如果你觉得需要拦截
                float up_x = getX();
                if (up_x - down_x >=100 ){
                    return true;
                }else {
                    return false ;
                }
            case MotionEvent.ACTION_UP:
                //如果你觉得需要拦截
                return true ;
        }

        return false;
    }
}
