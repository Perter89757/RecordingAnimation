package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: huang_yanhui
 * data:2017/6/27
 * time:15:33
 * emaill:huangyh@thinkive.com
 * description:
 */

public class MyButtonGroup extends ViewGroup{
    public MyButtonGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 子view相关的布局参数
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //第一步:获得测量模式和期待尺寸
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //第二步:计算子View的尺寸
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        //如果测量模式为warp_content
        int childCount = getChildCount();
        int widthsum = 0;
        int heightSum = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            widthsum += measuredWidth ;

            heightSum =  measuredHeight;
        }

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY?widthSize:widthsum,
                heightMode == MeasureSpec.EXACTLY?heightSize:heightSum);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //水平布局
        int childCount = getChildCount();
        int left =0;
        int right = 0;
        int height = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            //垂直
          //  child.layout(0, height, child.getMeasuredWidth(),height +  child.getMeasuredHeight());
            //  height += child.getMeasuredHeight();
            //水平
            child.layout(0+right,height,right+=child.getMeasuredWidth(),child.getMeasuredHeight());

        }
    }
}
