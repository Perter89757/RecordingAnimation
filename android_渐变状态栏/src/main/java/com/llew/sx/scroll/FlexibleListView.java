package com.llew.sx.scroll;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;

public class FlexibleListView extends ListView {

    private View mActionBar;
    private View mHeaderView;
    private int mMaxScrollHeight;
    private Drawable mActionBarBackground;

    public FlexibleListView(Context context) {
        super(context);
    }

    public FlexibleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlexibleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(null != mActionBarBackground) {
            mActionBarBackground.setAlpha(evaluateAlpha(Math.abs(mHeaderView.getTop())));
        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if(null != mHeaderView) {
            if(isTouchEvent && deltaY < 0) {
                mHeaderView.getLayoutParams().height += Math.abs(deltaY / 3.0);
                mHeaderView.requestLayout();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(null != mHeaderView) {
            int action = ev.getAction();
            if(MotionEvent.ACTION_UP == action || MotionEvent.ACTION_CANCEL == action) {
                resetHeaderViewHeight();
            }
        }
        return super.onTouchEvent(ev);
    }

    @SuppressLint("NewApi")
	private void resetHeaderViewHeight() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1);
        valueAnimator.setDuration(700);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float f = animation.getAnimatedFraction();
                mHeaderView.getLayoutParams().height -= f * (mHeaderView.getLayoutParams().height - mMaxScrollHeight);
                mHeaderView.requestLayout();

            }
        });
        valueAnimator.setInterpolator(new OvershootInterpolator());
        valueAnimator.start();
    }

    @Override
    public void addHeaderView(View v, Object data, boolean isSelectable) {
        super.addHeaderView(v, data, isSelectable);
        if(null == mHeaderView) {
            mHeaderView = v;
            mMaxScrollHeight = mHeaderView.getLayoutParams().height;
        }
    }

    private int evaluateAlpha(int t) {
        if (t >= mMaxScrollHeight) {
            return 255;
        }
        return (int) (255 * t /(float) mMaxScrollHeight);
    }

    public void bindActionBar(View actionBar) {
        if(null != actionBar) {
            mActionBar = actionBar;
            mActionBarBackground = actionBar.getBackground();
            if(null == mActionBarBackground) {
                mActionBarBackground = new ColorDrawable(Color.TRANSPARENT);
            }
            mActionBarBackground.setAlpha(0);
            if(Build.VERSION.SDK_INT >= 16) {
                // mActionBar.setBackground(mActionBarBackground);
            } else {
                mActionBar.setBackgroundDrawable(mActionBarBackground);
            }
        }
    }

    public void bindActionBar(ActionBar actionBar) {
        if(null != actionBar) {
            // TODO impl with ActionBar
            // actionBar.setBackgroundDrawable();
        }
    }
}
