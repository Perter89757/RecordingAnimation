package com.yiguo.recordinganimation.Switch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * author: huang_yanhui
 * data:2017/8/3
 * time:14:15
 * emaill:huangyh@thinkive.com
 * description:
 */

public class MeasureView extends View {
    public MeasureView(Context context) {
        super(context);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mywidthSize = 0;
        int myheightSize = 0;
        int defaultSize = 200;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY){
            Log.d("TAG","宽为EXACTLY");
        }
        if (widthMode == MeasureSpec.UNSPECIFIED){
            Log.d("TAG","宽为UNSPECIFIED");
        }
        if (widthMode == MeasureSpec.AT_MOST){
            Log.d("TAG","宽为AT_MOST");
        }
        if (heightMode == MeasureSpec.EXACTLY){
            Log.d("TAG","高为EXACTLYh");
        }
        if (heightMode == MeasureSpec.UNSPECIFIED){
            Log.d("TAG","高为UNSPECIFIEDh");
        }
        if (heightMode == MeasureSpec.AT_MOST){
            Log.d("TAG","高为AT_MOSTh");
        }

        myheightSize = heightsize;
        mywidthSize = widthsize;
        //测量一个正方形
        if (widthMode == MeasureSpec.AT_MOST) {
            mywidthSize = getMeasuredWidth();
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            myheightSize = getMeasuredHeight();
        }

        //
        if (myheightSize <= mywidthSize) {
            myheightSize = mywidthSize;
        }
        if (mywidthSize <= myheightSize) {
            mywidthSize = myheightSize;
        }

        setMeasuredDimension(mywidthSize, myheightSize);

    }
}
