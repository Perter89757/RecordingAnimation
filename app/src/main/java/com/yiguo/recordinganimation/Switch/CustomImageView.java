package com.yiguo.recordinganimation.Switch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.yiguo.recordinganimation.R;

/**
 * author: huang_yanhui
 * data:2017/8/4
 * time:15:14
 * emaill:huangyh@thinkive.com
 * description:
 */

public class CustomImageView extends View {

    private String textContent;
    private float textSize;
    private int textColor;
    private int imageView;
    private Bitmap mImage;
    private int mTextSize;
    private int defaultWidth;
    private int defaultHeight;
    private Rect rect;
    private Paint mPaint;
    private Rect mTextBound;
    private int widthsize;
    private int heightsize;

    public CustomImageView(Context context) {
        super(context);
        init();
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //图片文字都是ondraw()
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomImageView);
        textContent = typedArray.getString(R.styleable.CustomImageView_textConten);
        textColor = typedArray.getColor(R.styleable.CustomImageView_textColor, Color.BLUE);
        //获得图片尺寸
        mImage = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(R.styleable.CustomImageView_image,0));
        //获得文字尺寸
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.CustomImageView_textSize, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                16, getResources().getDisplayMetrics()));
        typedArray.recycle();
        init();
    }

    private void init() {
        rect = new Rect();
        mPaint = new Paint();
        mTextBound = new Rect();
        mPaint.setTextSize(mTextSize);
        // 计算了描绘字体需要的范围
        mPaint.getTextBounds(textContent, 0, textContent.length(), mTextBound);
        defaultWidth = Math.max(mTextBound.width(), mImage.getWidth());
        // defaultHeight = Math.max(mTextBound.height(), mImage.getHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        widthsize = MeasureSpec.getSize(widthMeasureSpec);
        heightsize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
            widthsize = defaultWidth+getPaddingLeft()+getPaddingRight();
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            heightsize = mImage.getHeight() + mTextBound.height() + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(widthsize, heightsize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 边框
         */
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(textColor);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(textContent, getMeasuredWidth() / 2 - mTextBound.width() * 1.0f / 2, getMeasuredHeight() - getPaddingBottom(), mPaint);
       //图片绘制的位置
        rect.left = getPaddingLeft();
        rect.right = widthsize - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = heightsize - getPaddingBottom();
        rect.bottom -= mTextBound.height();
        canvas.drawBitmap(mImage, null, rect, mPaint);

    }
}
