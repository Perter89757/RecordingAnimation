package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.ArrayList;

/**
 * SlideLayout
 * Created by D on 2017/5/19.
 */
public class SlideLayout2 extends ViewGroup {
    private int width;
    private int height;

    private final ArrayList<View> mMatchParentChildren = new ArrayList<>(1);
    private Scroller scroller;
    private int leftBorder;
    private int rightBorder;
    private int touchSlop;
    private int slideSlop;
    private int duration;

    // TouchEvent_ACTION_DOWN coordinates (dX, dY)
    private float dX, dY;

    // TouchEvent last coordinate (lastX, lastY)
    private float lastX;
    private boolean isMoveValid;
    private boolean isOpen;
    private int maxWidth;
    private int maxHeight;
    private float eX1;
    private float eY1;


    public SlideLayout2(Context context) {
        this(context, null);
    }

    public SlideLayout2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public static class LayoutParams extends MarginLayoutParams {

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        return new LayoutParams(lp);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 记录如果是wrap_content是设置的宽和高
         */
        int width = 0;
        int height = 0;
        int count_width = 0;
        int max_height = 0;

        int cCount = getChildCount();

        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;


        /**
         * 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
         */
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();
            count_width += cWidth + cParams.leftMargin + cParams.rightMargin;
            max_height = Math.max(cHeight, max_height);
        }
        width = Math.max(count_width, sizeWidth);
        height = Math.max(sizeHeight, max_height);
        /**
         * 如果是wrap_content设置为我们计算的值
         * 否则：直接设置为父容器计算的值
         */
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? sizeWidth
                : width, (heightMode == MeasureSpec.EXACTLY) ? sizeHeight
                : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft();
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();
            int width = childView.getMeasuredWidth();
            int height = childView.getMeasuredHeight();
            //左侧 : 父类的padding和自己的marginh和自己的宽度
            left = left + layoutParams.leftMargin;
            //右侧计算是从起点开始算的
            int right = left + width + layoutParams.rightMargin;
            int top = getPaddingTop() + layoutParams.topMargin;
            int bottom = top + height + layoutParams.bottomMargin;
            //
            childView.layout(left, top, right, bottom);
            //循环后 下一个
            left = left + right;
        }

        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getRight();
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            eX1 = ev.getRawX();
            eY1 = ev.getRawY();
            //  super.dispatchTouchEvent(ev);//传递给onInterceptTouchEvent
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eX2;
        float eY2;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //
                eX2 = event.getRawX();
                eY2 = event.getRawY();
                if ((eX2 - eX1) > 0) {//右侧,隐藏
                    scrollTo(0, 0);//最右边的View-整体宽度
                } else {
                    //向左滑动,显示右边的删除按钮
                    scrollTo(rightBorder - width, 0);//最右边的View-整体宽度
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
