package com.yiguo.recordinganimation.Switch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yiguo.recordinganimation.R;

/**
 * author: huang_yanhui
 * data:2017/8/4
 * time:9:51
 * emaill:huangyh@thinkive.com
 * description:
 */

public class CircleView extends View {

    private Paint mPaint;
    private int color;
    private int rad;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        color = typedArray.getColor(R.styleable.CircleView_circle_color, Color.BLUE);
        rad = typedArray.getInteger(R.styleable.CircleView_rad, 10);
        typedArray.recycle();
        // 在构造函数里初始化画笔的操作
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mywidthSize = 0;
        int myheightSize = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthsize = MeasureSpec.getSize(widthMeasureSpec);
        int heightsize = MeasureSpec.getSize(heightMeasureSpec);
        mywidthSize = widthsize;
        myheightSize = heightsize;
        if (widthMode == MeasureSpec.AT_MOST) {
            mywidthSize = 200;
            Log.d("CircleView","widthMode == MeasureSpec.AT_MOST");
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            myheightSize = 200;
            Log.d("CircleView","heightMode == MeasureSpec.AT_MOST");
        }
        setMeasuredDimension(mywidthSize, myheightSize);
    }

    private void init() {
        mPaint = new Paint();
        // 设置画笔颜色为蓝色
        mPaint.setColor(color);
        // 设置画笔宽度为10px
        mPaint.setStrokeWidth(5f);
        // 设置画笔模式为填充
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取控件的高度和宽度
        // 获取传入的padding值
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        int width = getWidth()-paddingLeft -paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int r = Math.min(width, height) / 2;
        canvas.drawCircle((width +paddingLeft+paddingRight) / 2, (height +paddingTop + paddingBottom)/ 2, rad, mPaint);
    }
}
