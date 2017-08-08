package com.yiguo.recordinganimation.popwindows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

/**
 * author: huang_yanhui
 * data:2017/7/28
 * time:16:48
 * emaill:huangyh@thinkive.com
 * description:
 */

public class PopupWindows extends PopupWindow {
    private Context mContext;
    private View view;

    public PopupWindows(Context context) {
        super(context);
        mContext  = context;
    }

    //自定义宽高,弹窗的宽高等于布局layout的参数

    @Override
    public int getWidth() {
        //return super.getWidth();
        return super.getWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight();
    }

    private void setView(int layoutId){
        view = LayoutInflater.from(mContext).inflate(layoutId, null, false);
        view.getMeasuredHeight();
        view.getMeasuredWidth();
    }

    private View getView() {
        return view;
    }



}
