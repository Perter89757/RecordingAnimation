package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author: huang_yanhui
 * data:2017/9/28
 * time:16:01
 * emaill:huangyh@thinkive.com
 * description:圆形加载进度条
 */

public class LoadingCircleView extends View {

    private Paint paint;
    private int loadingProgress = 5;
    private int MaxProgress = 100;
    private int progress;

    public LoadingCircleView(Context context) {
        super(context);
    }

    public LoadingCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //初始化画笔
        paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE); // 绘制空心圆
        paint.setStrokeWidth(10);//圆环的宽度

    }

    public void setLoadingProgress(int contentProgress) {
        loadingProgress = contentProgress;
        progress = loadingProgress / MaxProgress;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制一个圆环
        canvas.drawCircle(getWidth()/2,getHeight()/2,100,paint);
        //绘制圆弧进度条
        paint.setColor(Color.BLACK);
        RectF oval = new RectF(getWidth()/2-100,getHeight()/2-100,getWidth()/2+100,getHeight()/2+100);
        canvas.drawArc(oval,0,360*loadingProgress / MaxProgress,false,paint);//根据角度绘制圆弧

    }
}
