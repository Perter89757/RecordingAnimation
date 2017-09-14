package com.yiguo.recordinganimation.Switch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: huang_yanhui
 * data:2017/9/14
 * time:18:16
 * emaill:huangyh@thinkive.com
 * description:
 */

public class WeatherView extends View {

    private Paint mArcPaint;
    private int mWidth;
    private int mHeight;
    private int radius;
    private int startAngle;
    private int sweepAngle;

    public WeatherView(Context context) {
        this(context,null);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initPaint();
    }

    private void initPaint() {
        //
        startAngle =120;
        sweepAngle =300;
        //画圆
        mArcPaint =new Paint();
        mArcPaint.setColor(Color.BLUE);
        mArcPaint.setStrokeWidth(2);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setAntiAlias(true);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wrap_Len = 600;
        int width = measureDimension(wrap_Len, widthMeasureSpec);
        int height = measureDimension(wrap_Len, heightMeasureSpec);
        int len=Math.min(width,height);
        //保证是一个正方形
        setMeasuredDimension(len,len);
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = defaultSize;   //UNSPECIFIED
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布的大小
        mWidth =getWidth();
        mHeight =getHeight();
        //画圆
        radius =(mWidth-getPaddingLeft()-getPaddingRight())/2;//半径
        //以圆心为参考,绘制一个半径r的圆环
        canvas.translate(mWidth/2,mHeight/2);
        drawArcView(canvas);//画圆环
        drawLine(canvas);//画短线
    }

    private void drawLine(Canvas canvas) {

    }

    private void drawArcView(Canvas canvas) {
        RectF mRect=new RectF(-radius,-radius,radius,radius);
        //canvas.drawRect(mRect,mArcPaint);
        canvas.drawArc(mRect, startAngle, sweepAngle,false,mArcPaint);
    }
}
