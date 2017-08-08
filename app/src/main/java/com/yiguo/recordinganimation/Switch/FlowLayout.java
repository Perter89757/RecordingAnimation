package com.yiguo.recordinganimation.Switch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: huang_yanhui
 * data:2017/8/3
 * time:16:44
 * emaill:huangyh@thinkive.com
 * description:
 */

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //测量childView的大小
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        //获取实际宽度
        int totalWidth = 0;
        int totalHeight = 0;
        int maxHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            //累加宽度
            totalWidth += childView.getMeasuredWidth();
            //获得一行中的最大的高度的childView
            if (childView.getMeasuredHeight() >= maxHeight) {
                maxHeight = childView.getMeasuredHeight();
            }
            //判断累加的宽度是否达到父类的允许值
            if (totalWidth >= widthSize) {
                //换一行
                totalWidth = 0;//重新累计
                //累计高度
                totalHeight += maxHeight;
                maxHeight = 0;
            }
        }
        //退出计算
        //判断最终控件的尺寸
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? widthSize : totalWidth,
                (heightMode == MeasureSpec.EXACTLY) ? heightSize: totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //布局每一个childView
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
           // childView.layout();
        }

    }

}
