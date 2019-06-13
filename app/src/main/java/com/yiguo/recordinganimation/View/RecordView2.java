package com.yiguo.recordinganimation.View;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.yiguo.recordinganimation.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhaocheng on 2016/11/3.
 */

public class RecordView2 extends View {
    //View默认最小宽度
    private static final int DEFAULT_MIN_WIDTH = 500;
    public final static int MODEL_PLAY = 2;
    public final static int MODEL_RECORD = 1;
    private final TypedArray typedArray;
    //圆环的边距
    private int pandding = 10;
    //圆环的宽度
    private int widthing = 5;
    private Context mContext;
    private Paint mPaint;
    private final String TAG = "RecordView";
    private int countdownTime = 9;//倒计时时间，默认时间10秒
    private int countdownTime2 = 9;//倒计时时间，默认时间10秒.这是会变的
    private float progress = 0;//总进度360
    private boolean canDrawProgress = false;
    private double r;
    private Timer timeTimer = new Timer(true);
    private Timer progressTimer = new Timer(true);
    private long lastTime = 0;
    private int lineSpeed = 100;
    private float translateX = 0;
    /**
     * 圆环颜色
     */
    private int[] doughnutColors = new int[]{0xFFDAF6FE, 0xFF45C3E5, 0xFF45C3E5, 0xFF45C3E5, 0xFF45C3E5};
    /**
     * 默认是录制模式
     */
    private int model = MODEL_RECORD;
    /**
     * 计时器提示时间
     */
    private String hintText = "";
    /**
     * 进度条终点图片
     */
    private Drawable progressDrawable;
    /**
     * 振幅
     */
    private float amplitude = 1;
    /**
     * 音量
     */
    private float volume = 10;
    private int fineness = 1;
    private float targetVolume = 1;
    private float maxVolume = 100;
    private boolean isSet = false;
    /**
     * 灵敏度
     */
    private int sensibility = 4;
    private boolean canSetVolume = true;

    private TimerTask timeTask;
    private TimerTask progressTask;

    private float textHintSize;
    private float middleLineHeight;
    private int middleLineColor;
    private int voiceLineColor;
    private ArrayList<Path> paths;
    private int timeTextColor;
    private String unit;
    private String playHintText;

    public RecordView2(Context context) {
        this(context, null);
    }

    public RecordView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.recordView);
        initAtts();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//消除锯齿
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(5);//线条粗细
        //圆形外壳大小颜色线条粗细
        //中间有个文字倒计时
        //下方有动画波浪线
        mPaint.setTextSize(50);

    }

    private void initAtts() {
        model = typedArray.getInt(R.styleable.recordView_model, MODEL_RECORD);
        hintText = typedArray.getString(R.styleable.recordView_hintText);
        progressDrawable = typedArray.getDrawable(R.styleable.recordView_progressSrc) == null ?
                getResources().getDrawable(R.mipmap.light_blue) : typedArray.getDrawable(R.styleable.recordView_progressSrc);
        textHintSize = typedArray.getDimension(R.styleable.recordView_hintTextSize, 15);
        middleLineColor = typedArray.getColor(R.styleable.recordView_middleLineColor, getResources().getColor(R.color.RoundFillColor));
        voiceLineColor = typedArray.getColor(R.styleable.recordView_middleLineColor, getResources().getColor(R.color.RoundFillColor));
        middleLineHeight = typedArray.getDimension(R.styleable.recordView_middleLineHeight, 2);
        timeTextColor = typedArray.getColor(R.styleable.recordView_timeTextColor, getResources().getColor(R.color.TimeTextColor));
        unit = typedArray.getString(R.styleable.recordView_unit);
        playHintText = typedArray.getString(R.styleable.recordView_playHintText);
        paths = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            paths.add(new Path());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin) {
        int result = DEFAULT_MIN_WIDTH;//默认宽度
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制圆形
        int radius = getWidth() / 2;
        canvas.drawCircle(getWidth() / 2, getHeight()/2, radius-10, mPaint);

        //绘制"剩余时间"
        //绘制文字 xy表示文字起点的左下角位置
        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setTextSize(dip2px(mContext, 14));
        paint2.setColor(mContext.getResources().getColor(R.color.RoundFillColor));
        paint2.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("剩余时间",getWidth()/3,getHeight()/3,mPaint);

        //绘制动态倒计时
        Paint paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint3.setTextSize(dip2px(mContext, 18));
        paint3.setColor(mContext.getResources().getColor(R.color.colorAccent));
        paint3.setTextAlign(Paint.Align.CENTER);
        String time = "10";
        canvas.drawText(time,getWidth()/2,getHeight()/2,mPaint);
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
