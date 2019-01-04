package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.yiguo.recordinganimation.R;

import java.util.ArrayList;

/**
 * SlideLayout
 * Created by D on 2017/5/19.
 */
public class SlideLayout extends ViewGroup {
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
    private OnStateChangeListener listener;

    public SlideLayout(Context context) {
        this(context, null);
    }

    public SlideLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //   initTypedArray(context, attrs);
        init(context);
    }

    private void initTypedArray(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlideLayout);
        slideSlop = (int) typedArray.getDimension(R.styleable.SlideLayout_sl_slideSlop,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics()));
        duration = typedArray.getInteger(R.styleable.SlideLayout_sl_duration, 250);
        typedArray.recycle();
    }

    private void init(Context context) {
        scroller = new Scroller(context);
        //  touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
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
        int count = getChildCount();
        //测量模式不是精准类型,是AT_MOST模式 需要重新定义View尺寸
        final boolean measureMatchParentChildren = MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY ||
                MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY;

        mMatchParentChildren.clear();

        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        //循环遍历View,获取到所有View中最大的宽高尺寸
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                //该方法作用是测绘子View，在父View的onMeasure中循环调用，达到遍历的效果。
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                //这个lp获取前必须要重写generateLayoutParams和定义自己的layotparams
                final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                maxWidth = Math.max(maxWidth, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    //循环测量子类View的状态
                    childState = combineMeasuredStates(childState, child.getMeasuredState());
                }

                if (measureMatchParentChildren) {
                    //父类是wrap_content,子类View是mathc_parent
                    // TODO: 2018/8/21  父类和子类都是wrap_content,怎么处理
                    if (lp.width == MarginLayoutParams.MATCH_PARENT || lp.height == MarginLayoutParams.MATCH_PARENT) {
                        mMatchParentChildren.add(child);
                    }
                }
            }
        }

        // 获得view的最大尺寸宽高 和系统推荐的最小尺寸比较
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            // Check against our foreground's minimum height and width
//            final Drawable drawable = getForeground();
//            if (drawable != null) {
//                maxHeight = Math.max(maxHeight, drawable.getMinimumHeight());
//                maxWidth = Math.max(maxWidth, drawable.getMinimumWidth());
//            }
//        }
        //设置整个SildeLayout的尺寸setMeasuredDimension 和 resolveSizeAndState()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                    resolveSizeAndState(maxHeight, heightMeasureSpec, childState << MEASURED_HEIGHT_STATE_SHIFT));
        } else {
            // setMeasuredDimension(width, height);
        }

        //需要重新测量的View集合
        count = mMatchParentChildren.size();
        if (count > 1) {
            for (int i = 0; i < count; i++) {
                final View child = mMatchParentChildren.get(i);
                final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                final int childWidthMeasureSpec;
                if (lp.width == SlideLayout.LayoutParams.MATCH_PARENT) {
                    final int width = Math.max(0, getMeasuredWidth() - lp.leftMargin - lp.rightMargin);
                    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
                } else {
                    childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, lp.leftMargin + lp.rightMargin, lp.width);
                }

                final int childHeightMeasureSpec;
                if (lp.height == SlideLayout.LayoutParams.MATCH_PARENT) {
                    final int height = Math.max(0, getMeasuredHeight()
                            - lp.topMargin - lp.bottomMargin);
                    childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                            height, MeasureSpec.EXACTLY);
                } else {
                    childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                            lp.topMargin + lp.bottomMargin,
                            lp.height);
                }

                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }

        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        if (!changed || count <= 0) {
            return;
        }

        final int parentLeft = getPaddingLeft();
        final int parentRight = r - l - getPaddingRight();

        final int parentTop = getPaddingTop();
        final int parentBottom = b - t - getPaddingBottom();

        int left = 0, top = 0;
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();

                final int width = child.getMeasuredWidth();
                final int height = child.getMeasuredHeight();

                int childLeft;
                int childTop;

                childTop = parentTop + lp.topMargin;
                childLeft = parentLeft + lp.leftMargin;

                // Layout horizontally for each child view in the ViewGroup
                child.layout(left + childLeft, childTop, left + childLeft + width, childTop + height);

                left += childLeft + width + lp.rightMargin + getPaddingRight();
            }
        }
        // Initialize left and right boundary values
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(count - 1).getRight();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (listener != null && listener.closeAll(this)) {
                return false;//指向上一层(Activity)的onTouchEvent,不继续传递
            } else {
                final float eX = ev.getRawX();
                final float eY = ev.getRawY();
                lastX = dX = eX;
                dY = eY;
                super.dispatchTouchEvent(ev);//传递给onInterceptTouchEvent
                return true;//在此消费 不传递
            }
        }
        return super.dispatchTouchEvent(ev);//传递给onInterceptTouchEvent()
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            final float eX = ev.getRawX();
            final float eY = ev.getRawY();
            // Intercept child event when horizontal ACTION_MOVE value is greater than TouchSlop
            if (Math.abs(eX - dX) > touchSlop && Math.abs(eX - dX) > Math.abs(eY - dY)) {
                return true;//传递给onTouchEvent()处理
            }
        }
        return super.onInterceptTouchEvent(ev);//传递给下一层的View
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float eX = event.getRawX();
        final float eY = event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!isMoveValid && Math.abs(eX - dX) > touchSlop && Math.abs(eX - dX) > Math.abs(eY - dY)) {
                    // Disable parent view interception events
                    //该方法可以在子类通过调用getParent().requestDisallInterceptRouchEvent(true) 来让父布局禁用拦截事件功能
                    requestDisallowInterceptTouchEvent(true);
                    isMoveValid = true;
                }
                if (isMoveValid) {
                    int offset = (int) (lastX - eX);
                    lastX = eX;
                    if (getScrollX() + offset < 0) {
                        toggle(false, false);//关闭
                        dX = eX; // reset eX
                    } else if (getScrollX() + offset > rightBorder - width) {
                        toggle(true, false);//触发侧滑视图
                        dX = eX; // reset eX
                    } else {
                        scrollBy(offset, 0);
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (isMoveValid) {
                    if (eX - dX < -slideSlop) {
                        toggle(true, true);
                    } else if (eX - dX > slideSlop) {
                        toggle(false, true);
                    } else {
                        toggle(isOpen, true);
                    }
                    isMoveValid = false;
                    return true;//在此处消费
                }
                break;
        }
        return super.onTouchEvent(event);//传递给上一层的onTouchEvent()处理
    }

    private void toggle(boolean open, boolean withAnim) {
        if (isOpen != open && listener != null) {
            listener.onChange(this, open);
        }
        isOpen = open;
        if (isOpen) {
            if (withAnim) {
                smoothScrollTo(rightBorder - width, duration);
            } else {
                scrollTo(rightBorder - width, 0);//最右边的View-整体宽度
            }
        } else {
            if (withAnim) {
                smoothScrollTo(0, duration);
            } else {
                scrollTo(0, 0);
            }
        }
    }

    private void smoothScrollTo(int dstX, int duration) {
        int offset = dstX - getScrollX();
        scroller.startScroll(getScrollX(), 0, offset, 0, duration);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Open or close
     *
     * @param open     Open or close
     * @param withAnim Whether animation
     */
    public void setOpen(boolean open, boolean withAnim) {
        toggle(open, withAnim);
    }

    public void open() {
        toggle(true, true);
    }

    public void close() {
        toggle(false, true);
    }

    public interface OnStateChangeListener {

        void onChange(SlideLayout layout, boolean isOpen);

        /**
         * Close all slides that are not closed
         *
         * @param layout this
         * @return true: there is a slide that is not closed; false: there is no slide that is not closed
         */
        boolean closeAll(SlideLayout layout);
    }

    public void setOnStateChangeListener(OnStateChangeListener listener) {
        this.listener = listener;
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
}
