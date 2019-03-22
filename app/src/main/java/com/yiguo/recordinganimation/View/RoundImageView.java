package com.yiguo.recordinganimation.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;



/**
 * Created by huang_yanhui on 2019/3/20.
 */
public class RoundImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint paint;
    private Bitmap bitmap;
    private Matrix localM;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init() {
        paint = new Paint();
        localM = new Matrix();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin) {
        int result = 500;//默认宽度
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
        Drawable drawable = getDrawable();
        if (drawable == null)return;
        if (drawable instanceof BitmapDrawable) {
            //drawable 转成 bitmap
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int w = drawable.getIntrinsicWidth();
            int h = drawable.getIntrinsicHeight();
             bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            Canvas canvasbitMap = new Canvas(bitmap);
            drawable.setBounds(0, 0, w, h);
            drawable.draw(canvasbitMap);
        }

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        float scale = getMeasuredWidth() * 1.0f / bitmap.getWidth();
        localM.setScale(scale,scale);//缩放图片
        bitmapShader.setLocalMatrix(localM);
        paint.setShader(bitmapShader);

        RectF rectF = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rectF, 30, 30, paint);
        RectF rectFLeftTOP = new RectF(0, 0, 30, 30);//左上角直角
        canvas.drawRect(rectFLeftTOP,paint);
        RectF rectFRightTOP = new RectF(getWidth()-30, 0, getWidth(), 30);//右上角直角
        canvas.drawRect(rectFRightTOP,paint);
    }
}
