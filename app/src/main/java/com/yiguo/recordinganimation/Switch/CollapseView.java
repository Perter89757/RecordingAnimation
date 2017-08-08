package com.yiguo.recordinganimation.Switch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yiguo.recordinganimation.R;

/**
 * author: huang_yanhui
 * data:2017/8/3
 * time:10:52
 * emaill:huangyh@thinkive.com
 * description:
 */

public class CollapseView extends LinearLayout {

    private final Context mContext;
    private TextView mNumberTextView;
    private TextView mTitleTextView;
    private RelativeLayout mTitleRelativeLayout;
    private RelativeLayout mContentRelativeLayout;
    private ImageView mArrowImageView;
    private int parentWidthMeasureSpec;
    private int parentHeightMeasureSpec;

    public CollapseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.collapse_view, this);
        initView();
    }

    private void initView() {
        mNumberTextView = (TextView) findViewById(R.id.numberTextView);
        mTitleTextView = (TextView) findViewById(R.id.titleTextView);
        mArrowImageView = (ImageView) findViewById(R.id.arrowImageView);
        mTitleRelativeLayout = (RelativeLayout) findViewById(R.id.titleRelativeLayout);
        mContentRelativeLayout = (RelativeLayout) findViewById(R.id.contentRelativeLayout);
        //点击标题栏
        mTitleRelativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContentRelativeLayout.getVisibility() == View.GONE) {
                    //展开
                    expand(mContentRelativeLayout);
                } else {
                    //隐藏
                    hide(mContentRelativeLayout);
                }
            }


        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        parentWidthMeasureSpec = widthMeasureSpec;
        parentHeightMeasureSpec = heightMeasureSpec;

    }

    private void expand(final View view) {
        view.measure(parentWidthMeasureSpec, parentHeightMeasureSpec);
        final int measuredWidth = view.getMeasuredWidth();//一样的源码
        // MeasureSpec.getSize(parentHeightMeasureSpec) 和view.getMeasuredWidth();//一样的源码

        final int measuredHeight = view.getMeasuredHeight();
        int widthMode = MeasureSpec.getMode(parentWidthMeasureSpec);//不是隐藏视图
        int heightMode = MeasureSpec.getMode(parentHeightMeasureSpec);

        // int widthsize = MeasureSpec.getSize(parentWidthMeasureSpec);
        //int heightsize = MeasureSpec.getSize(parentHeightMeasureSpec);

        view.getLayoutParams().width = measuredWidth;
        view.getLayoutParams().height = measuredHeight;
        view.setVisibility(View.VISIBLE);

//        Animation animation = new Animation() {
//            @Override
//            protected void applyTransformation(float interpolatedTime, Transformation t) {
//                super.applyTransformation(interpolatedTime, t);
//                if (interpolatedTime == 1) {
//                    view.getLayoutParams().height = measuredHeight;
//                } else {
//                    view.getLayoutParams().height = (int) (measuredHeight * interpolatedTime);
//                }
//                view.requestLayout();
//            }
//
//            @Override
//            public boolean willChangeBounds() {
//                return true;
//            }
//        };
//
//        animation.setDuration(duration);
//        view.startAnimation(animation);
    }

    private void hide(View view) {
        view.setVisibility(View.GONE);
    }

    public void setContent(int resID) {
        View view = LayoutInflater.from(mContext).inflate(resID, null);
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        mContentRelativeLayout.addView(view);
    }

}
