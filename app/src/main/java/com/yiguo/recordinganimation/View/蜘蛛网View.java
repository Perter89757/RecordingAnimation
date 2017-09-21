package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author crazychen
 * @version 1.0
 * @date 2015/12/2
 */
public class 蜘蛛网View extends View {
    private int count = 6;                //数据个数
    private float angle = (float) (Math.PI * 2 / count);//多边形的每个角的弧度
    private float radius;                   //网格最大半径
    private int centerX;                  //中心X
    private int centerY;                  //中心Y
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private double[] data = {100, 60, 60, 60, 100, 50, 10, 20}; //各维度分值
    private float maxValue = 100;             //数据最大值
    private Paint mainPaint;                //雷达区画笔
    private Paint valuePaint;               //数据区画笔
    private Paint textPaint;                //文本画笔

    public 蜘蛛网View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public 蜘蛛网View(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public 蜘蛛网View(Context context) {
        super(context);
        init();
    }


    //初始化
    private void init() {
        count = Math.min(data.length, titles.length);

        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.GRAY);
        mainPaint.setStyle(Paint.Style.STROKE);

        valuePaint = new Paint();
        valuePaint.setAntiAlias(true);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        textPaint = new Paint();
        textPaint.setTextSize(30);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.9f;//半径取宽高的最小值,然后除以2
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();//当尺寸变化时,重新绘制draw
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawPolygon(canvas);
          drawLines(canvas);
          drawText(canvas);
        // drawRegion(canvas);
    }

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        //把半径分割出5等分
        float r1 = radius/(count -1);
        for (int i = 0; i <  count; i++) {
            float r = r1*i;
            path.reset();
            path.moveTo(centerX+r, centerY);
            for (int j = 0; j <= count; j++) {
                float x = (float) (centerX + r * Math.cos(angle * j));
                float y = (float) (centerY + r * Math.sin(angle * j));
                path.lineTo(x,y);
            }
            canvas.drawPath(path, mainPaint);
        }


//        float r = radius/(count-1); //分为5个等分的半径
//        for(int i=1;i<count;i++){//绘制i个多边形
//            float curR = r*i;
//            path.reset();
//            for(int j=0;j<count;j++){
//                if(j==0){
//                    path.moveTo(centerX+curR,centerY);
//                }else{
//                    float x = (float) (centerX+curR*Math.cos(angle*j));//根据不同的半径,绘制多边形的点
//                    float y = (float) (centerY+curR*Math.sin(angle*j));
//                    path.lineTo(x,y);
//                }
//            }
//            path.close();
//            canvas.drawPath(path, mainPaint);
//        }
    }

    /**
     * 绘制直线
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();

        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX,centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x,y);
            canvas.drawPath(path,mainPaint);
        }

//        for (int i = 0; i < count; i++) {
//            path.reset();
//            path.moveTo(centerX, centerY);
//            float x = (float) (centerX + radius * Math.cos(angle * i));//多边形的点到中心点的距离
//            float y = (float) (centerY + radius * Math.sin(angle * i));
//            path.lineTo(x, y);
//            canvas.drawPath(path, mainPaint);
//        }
    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {//改变最外面radiusd最大的多边形的角度
            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {//第4象限,右下角
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {//第3象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {//第2象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {//第1象限
                float dis = textPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, textPaint);
            }
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);//最大半径的多边形
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            //绘制小圆点
            canvas.drawCircle(x, y, 10, valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }

    //设置标题
    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    //设置数值
    public void setData(double[] data) {
        this.data = data;
    }


    public float getMaxValue() {
        return maxValue;
    }

    //设置最大数值
    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    //设置蜘蛛网颜色
    public void setMainPaintColor(int color) {
        mainPaint.setColor(color);
    }

    //设置标题颜色
    public void setTextPaintColor(int color) {
        textPaint.setColor(color);
    }

    //设置覆盖局域颜色
    public void setValuePaintColor(int color) {
        valuePaint.setColor(color);
    }
}