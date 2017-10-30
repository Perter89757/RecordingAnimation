package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * author: huang_yanhui
 * data:2017/10/11
 * time:10:29
 * emaill:huangyh@thinkive.com
 * description:日出日落动画
 */

public class SunAnimationView extends View {

    private Paint paint;
    private int radius;
    private int magrinTop;

    public SunAnimationView(Context context) {
        super(context);
    }

    public SunAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context) {
        paint = new Paint();
        radius = dp2px(context,150);
        magrinTop = dp2px(context,10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //
        int width = getWidth();
        int height = getHeight();
        int left = getWidth()/2-radius;
        int top = getHeight()/2 -radius-magrinTop;
        int right = getWidth()/2 + radius;
        int bottmm = getHeight()/2;
        RectF rectF = new RectF(left,top,right,bottmm);
    }
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
