package com.yiguo.recordinganimation.Switch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: huang_yanhui
 * data:2017/8/3
 * time:14:38
 * emaill:huangyh@thinkive.com
 * description:
 */

public class VLinearLayout extends ViewGroup {


    public VLinearLayout(Context context) {
        super(context);
    }

    public VLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
        //不是viewGroup父类对childView子类的测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY){
            Log.d("TAG","EXACTLY");
        }
        if (widthMode == MeasureSpec.UNSPECIFIED){
            Log.d("TAG","UNSPECIFIED");
        }
        if (widthMode == MeasureSpec.AT_MOST){
            Log.d("TAG","AT_MOST");
        }
        if (heightMode == MeasureSpec.EXACTLY){
            Log.d("TAG","EXACTLYh");
        }
        if (heightMode == MeasureSpec.UNSPECIFIED){
            Log.d("TAG","UNSPECIFIEDh");
        }
        if (heightMode == MeasureSpec.AT_MOST){
            Log.d("TAG","AT_MOSTh");
        }

        //计算出xml布局中childView的尺寸
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                int height = getTotleHeight();
                int width = getMaxChildWidth();
                setMeasuredDimension(width,height);
            }else if (widthMode == MeasureSpec.AT_MOST){
                setMeasuredDimension(getMaxChildWidth(),heightSize);
            }else if (heightMode == MeasureSpec.AT_MOST){
                setMeasuredDimension(widthSize,getTotleHeight());
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        //记录当前的高度位置
        int curHeight = 0;
        //将子View逐个摆放
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();
            //摆放子View，参数分别是子View矩形区域的左、上、右、下边
            child.layout(l, curHeight, l + width, curHeight + height);
            curHeight += height;
        }
    }

    public int getTotleHeight() {
        int totleHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            totleHeight += childView.getMeasuredHeight();
        }
        return totleHeight;
    }

    public int getMaxChildWidth() {
        int maxWidth = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int measuredWidth = childView.getMeasuredWidth();
            if (measuredWidth > maxWidth) {
                maxWidth = measuredWidth;
            }
        }
        return maxWidth;
    }
}
