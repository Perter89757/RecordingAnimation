package com.llew.sx.scroll;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

@SuppressLint("Override")
public class FlexibleScrollView extends ScrollView {

    private static final float DEFAULT_LOAD_FACTOR = 3.0F;
    private static final int DEFAULT_SCROLL_HEIGHT = 500;

    private View mHeaderView;
    private int mOriginHeight;
    private int mZoomedHeight;
    
    private Drawable mActionBarBackground;
    private int mMaxScrollHeight = DEFAULT_SCROLL_HEIGHT;

    public FlexibleScrollView(Context context) {
        super(context);
    }

    public FlexibleScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlexibleScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
    }
    
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(null != mActionBarBackground) {
            mActionBarBackground.setAlpha(evaluateAlpha(Math.abs(getScrollY())));
        }
    }

	@Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if(null != mHeaderView) {
            if(isTouchEvent && deltaY < 0) {
            	mHeaderView.getLayoutParams().height += Math.abs(deltaY / DEFAULT_LOAD_FACTOR);
            	mHeaderView.requestLayout();
                mZoomedHeight = mHeaderView.getHeight();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @SuppressLint("ClickableViewAccessibility")
	@Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(null != mHeaderView && 0 != mOriginHeight && 0 != mZoomedHeight) {
            int action = ev.getAction();
            if(MotionEvent.ACTION_UP == action || MotionEvent.ACTION_CANCEL == action) {
                resetHeaderViewHeight();
            }
        }
        return super.onTouchEvent(ev);
    }
    
    public void bindActionBar(View actionBar) {
        if(null != actionBar) {
            mActionBarBackground = actionBar.getBackground();
            if(null == mActionBarBackground) {
                mActionBarBackground = new ColorDrawable(Color.TRANSPARENT);
            }
            mActionBarBackground.setAlpha(0);
            if(Build.VERSION.SDK_INT >= 16) {
                // actionBar.setBackground(mActionBarBackground);
            } else {
            	actionBar.setBackgroundDrawable(mActionBarBackground);
            }
        }
    }
    
    public void setHeaderView(View headerView) {
    	this.mHeaderView = headerView;
    	updateHeaderViewHeight();
    }

    private void updateHeaderViewHeight() {
        mOriginHeight = null == mHeaderView ? 0 : mHeaderView.getHeight();
        if(0 == mOriginHeight && null != mHeaderView) {
            post(new Runnable() {
                @Override
                public void run() {
                    mOriginHeight = mHeaderView.getHeight();
                }
            });
        }
    }
    
    private int evaluateAlpha(int t) {
        if (t >= mMaxScrollHeight) {
            return 255;
        }
        return (int) (255 * t /(float) mMaxScrollHeight);
    }

    @SuppressLint("NewApi")
	private void resetHeaderViewHeight() {
        if(mHeaderView.getLayoutParams().height != mOriginHeight) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(mZoomedHeight, mOriginHeight);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                	mHeaderView.getLayoutParams().height = (Integer) animation.getAnimatedValue();
                	mHeaderView.requestLayout();
                }
            });
            valueAnimator.setDuration(200);
            valueAnimator.start();
        }
    }
}
