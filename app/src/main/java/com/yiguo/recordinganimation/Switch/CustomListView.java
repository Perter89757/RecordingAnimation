package com.yiguo.recordinganimation.Switch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.yiguo.recordinganimation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author: huang_yanhui
 * data:2017/8/2
 * time:16:07
 * emaill:huangyh@thinkive.com
 * description:
 */

public class CustomListView extends ListView implements View.OnTouchListener, GestureDetector.OnGestureListener {

    private boolean isDeleteShown;
    private final GestureDetector gestureDetector;
    private int mSelectedItem;
    private View mDeleteBtn;
    private ViewGroup mItemLayout;
    private float downX;
    private List<Boolean> isShow = new ArrayList<>();
    private int mSelectedItemOld;

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 创建手势监听器对象
        gestureDetector = new GestureDetector(context, this);
        //触摸监听
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isDeleteShown) {
            hideDelete();//隐藏
            return false;
        } else
            return gestureDetector.onTouchEvent(event);//交给手势监听
    }

    void hideDelete() {
        mItemLayout.removeView(mDeleteBtn);
        mDeleteBtn = null;
        isDeleteShown = false;
    }
    // 删除事件监听器
    public interface OnDeleteListener {
        void onDelete(int index);
    }
    private OnDeleteListener mOnDeleteListener;
    // 设置删除监听事件
    public void setOnDeleteListener(OnDeleteListener listener) {
        mOnDeleteListener = listener;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        //没有显示,滑动不同的item 显示不同
        //显示,滑动其他的隐藏现在的,显示其他的

        if (!isDeleteShown) {//没有显示
            mSelectedItemOld = pointToPosition((int) e.getX(), (int) e.getY());
        }else {
            //已经显示了,再次获得当前的位置,是否是同一个item
            mSelectedItem = pointToPosition((int) e.getX(), (int) e.getY());
            if (mSelectedItem == mSelectedItemOld){
                //同一个item
            }else {
                //不同的item
            }
        }

        downX = e.getX();
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float v = downX - velocityX;
        if (v > 0){
            //向左边滑动,显示删除
            isDeleteShown = false;
        }else {
            //向右滑动,隐藏删除
            isDeleteShown = true;
        }
        // 如果当前删除按钮没有显示出来，并且x方向滑动的速度大于y方向的滑动速度
        if (!isDeleteShown && Math.abs(velocityX) > Math.abs(velocityY) ) {
            mDeleteBtn = LayoutInflater.from(getContext()).inflate(
                    R.layout.delete_btn, null);

            mDeleteBtn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    mItemLayout.removeView(mDeleteBtn);
                    mDeleteBtn = null;
                    isDeleteShown = false;
                    if (mOnDeleteListener != null) {
                        mOnDeleteListener.onDelete(mSelectedItem);
                    }
                }
            });

            mItemLayout = (ViewGroup) getChildAt(mSelectedItem - getFirstVisiblePosition());

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            mItemLayout.addView(mDeleteBtn, params);
            isDeleteShown = true;
        }else {

        }

        return false;
    }


    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    public boolean isDeleteShown() {
        return isDeleteShown;
    }
}
