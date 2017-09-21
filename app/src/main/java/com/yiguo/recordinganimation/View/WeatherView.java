package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yiguo.recordinganimation.R;

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
    private int count;
    private Paint mLinePaint;
    private int ocAngle;//0度初始角
    private int fgAngle;//总覆盖的角
    private int minTemp;
    private int maxTemp;
    private int currentTemp;//当前温度
    private TextPaint mTextPaint;
    private Bitmap bitmap;
    private int offset;
    private int startAngle1;

    public WeatherView(Context context) {
        this(context, null);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        initPaint(context);
    }

    private void initPaint(Context context) {
        startAngle = 120;
        sweepAngle = 300;
        count = 60;//刻度份数

        currentTemp = 2;
        maxTemp = 50;
        minTemp = -3;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        ocAngle = 230;
        fgAngle = 90;
        offset = 22;
        //画圆
        mArcPaint = new Paint();
        mArcPaint.setColor(Color.BLUE);
        mArcPaint.setStrokeWidth(2);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setAntiAlias(true);
        //画刻度线
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setAntiAlias(true);
        //绘制文字
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLUE);
        mTextPaint.setStrokeWidth(4);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(144);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int wrap_Len = 600;
        int width = measureDimension(wrap_Len, widthMeasureSpec);
        int height = measureDimension(wrap_Len, heightMeasureSpec);
        int len = Math.min(width, height);
        //保证是一个正方形
        setMeasuredDimension(len, len);
    }

    private int measureDimension(int defaultSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = defaultSize;   //UNSPECIFIED
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画布的大小
        mWidth = getWidth();
        mHeight = getHeight();
        radius = (mWidth - getPaddingLeft() - getPaddingRight()) / 2;//半径
        canvas.translate(mWidth / 2, mHeight / 2); //以圆心为参考,绘制一个半径r的圆环
        drawArcView(canvas);//画圆环
        drawLine(canvas);//画短线
        drawTextBitmapView(canvas);//画中间的温度和下边的图片

        //绘制刻度盘上的文字,最高温和最低温
        //需要知道零摄氏度的刻度位置.零摄氏度为230°
        //获取对应的坐标
        //根据最高最低温度获得角度范围
        drawTodayTemp(canvas);
    }


    private void drawTodayTemp(Canvas canvas) {
        mTextPaint.setTextSize(24);
        //最低温度的起始角度
        startAngle1 = getStartAngle(minTemp, maxTemp);
        canvas.drawText(minTemp + "℃", getCosX(startAngle1) + 25, getSinY(startAngle1), mTextPaint);
        canvas.drawText(maxTemp + "℃", getCosX(startAngle1 + fgAngle) + 25, getSinY(startAngle1 + fgAngle), mTextPaint);

        int circleAngle = startAngle1+(currentTemp-minTemp)*fgAngle/(maxTemp-minTemp);
        canvas.drawCircle(getCosX(circleAngle)-25, getSinY(circleAngle),5,mArcPaint);
    }

    //根据当天温度范围获得扇形开始角。
    private int getStartAngle(int minTemp, int maxTemp) {
        int startFgAngle = 0;
        if (minTemp >= maxTemp) {
            Log.e("ws", "getStartAngle---?fail");
            return startFgAngle;
        }

        if (minTemp <= 0) {
            //最低和最高温的角度为90,气温低于零度,ocAngle=230
            //230 减去 最高最低温度在90度中所占的比例
            //温度范围-2,4  90/6  其中最低温度占据了2  -->最低温度占据30度
            //230-30 最低温度的起始角度为200°
            startFgAngle = ocAngle - (0 - minTemp) * fgAngle / (maxTemp - minTemp);
        } else {
            startFgAngle = ocAngle + (minTemp - 0) * fgAngle / (maxTemp - minTemp);
        }
        //边界 start
        if (startFgAngle <= startAngle) {//如果开始角小于startAngle，防止过边界
            startFgAngle = startAngle + 10;
        } else if ((startFgAngle + fgAngle) >= (startAngle + sweepAngle)) {//如果结束角大于(startAngle+sweepAngle)
            startFgAngle = startAngle + sweepAngle - 20 - fgAngle;
        }
        //边界 end
        return startFgAngle;
    }


    //根据角获得X坐标  R*cos&+getTextPaintOffset(mTextPaint)-off
    private float getCosX(int Angle) {
        return (float) (radius * Math.cos(Angle * Math.PI / 180));
    }

    private float getSinY(int Angle) {
        return (float) (radius * Math.sin(Angle * Math.PI / 180));
    }


    private void drawTextBitmapView(Canvas canvas) {
        //以圆心为坐标绘制
        mTextPaint.setTextSize(144);
        canvas.drawText(currentTemp + "℃", 0, 0 + getTextPaintOffset(mTextPaint), mTextPaint);
        //图片的位置,left是圆心的两侧,顶点的高度是圆心减去图片的高度
        canvas.drawBitmap(bitmap, 0 - bitmap.getWidth() / 2, radius - bitmap.getHeight() / 2 - 50, null);
    }

    /**
     * 获得文字的高度
     *
     * @param paint
     * @return
     */
    public float getTextPaintOffset(Paint paint) {
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        return -fontMetrics.descent + (fontMetrics.bottom - fontMetrics.top) / 2;
    }

    private void drawLine(Canvas canvas) {
        canvas.save();
        float angle = (float) sweepAngle / count;//圆盘刻度间隔,60份
        canvas.rotate(-270 + startAngle);//将起始刻度点旋转到正上方
        for (int i = 0; i <= count; i++) {
            if (i == 0 || i == count) {//起点和终点
                mLinePaint.setStrokeWidth(1);
                mLinePaint.setColor(Color.BLUE);
                //坐标系已经是在圆Y轴半径的正上方,只需要绘制一条Y轴的线段
                canvas.drawLine(0, -radius, 0, -radius + 50, mLinePaint);
            } else if ((startAngle + angle * i) >= startAngle1 && (startAngle + angle * i) <= startAngle1 + fgAngle) {
                //改变最高最低温度区间的颜色
                mLinePaint.setColor(Color.GREEN);
                mLinePaint.setStrokeWidth(5);
                canvas.drawLine(0, -radius, 0, -radius + 30, mLinePaint);
            } else {
                mLinePaint.setStrokeWidth(2);
                mLinePaint.setColor(Color.BLUE);
                canvas.drawLine(0, -radius, 0, -radius + 30, mLinePaint);
            }
            canvas.rotate(angle);//逆时针旋转画布
        }
        canvas.restore();
    }

    private void drawArcView(Canvas canvas) {
        //正方形参数
        RectF mRect = new RectF(-radius, -radius, radius, radius);
        //canvas.drawRect(mRect,mArcPaint);绘制正方形
        canvas.drawArc(mRect, startAngle, sweepAngle, false, mArcPaint);
    }
}
