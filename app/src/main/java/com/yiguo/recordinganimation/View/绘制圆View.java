package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: huang_yanhui
 * data:2017/9/13
 * time:14:43
 * emaill:huangyh@thinkive.com
 * description:
 */

public class 绘制圆View extends View {

    private Paint mPaint1;

    public 绘制圆View(Context context) {
        super(context);
        init();
    }

    public 绘制圆View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // 创建画笔
        mPaint1 = new Paint();
        // 设置画笔颜色为蓝色
        mPaint1.setColor(Color.BLACK);
        // 设置画笔宽度为10px
        mPaint1.setStrokeWidth(5f);
        //设置画笔模式为填充
        mPaint1.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int hightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //重点判断自适应
        if (widthMode == MeasureSpec.AT_MOST) {
            //设置默认尺寸
            widthSize = getMeasuredWidth();
        }
        if (hightMode == MeasureSpec.AT_MOST) {
            heightSize = 100;
        }
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //圆心的坐标,半径,画笔
        int width = getWidth();
        int height = getHeight();
        //获取padding
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int cx = width-paddingLeft-paddingRight;
        int cy = height-paddingTop- paddingBottom;
        int r = Math.min(cx, cy) / 2;
        canvas.drawCircle(width / 2, height / 2, r, mPaint1);
    }
}
