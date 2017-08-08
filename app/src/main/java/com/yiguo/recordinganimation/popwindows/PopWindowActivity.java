package com.yiguo.recordinganimation.popwindows;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Switch;

import com.yiguo.recordinganimation.R;

public class PopWindowActivity extends AppCompatActivity {

    private PopupWindow popupWindow;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);
        button = (Button) findViewById(R.id.more);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {
                // window(button);
                initView(button);


            }
        });
    }

    private void window(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPopupWindow.Builder(PopWindowActivity.this)
                .setView(R.layout.listview_pop)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                    @Override
                    public void getChildView(View view, int layoutResId) {

                    }
                })
                .create();
        //在view的左下角为原点,x偏移一个按钮宽度的距离,Y往上偏移一个按钮高度
        // popupWindow.showAsDropDown(view, view.getWidth(), -view.getHeight());
        //
        popupWindow.showAsDropDown(view, 0, -view.getHeight());
    }

    private void initView(View targetView) {
//准备PopupWindow的布局View
        View popupView = LayoutInflater.from(this).inflate(R.layout.listview_pop, null);
//初始化一个PopupWindow，width和height都是WRAP_CONTENT
        PopupWindow popupWindow = new PopupWindow(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//设置PopupWindow的视图内容
        popupWindow.setContentView(popupView);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
//点击空白区域PopupWindow消失，这里必须先设置setBackgroundDrawable，否则点击无反应
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setOutsideTouchable(true);
//设置PopupWindow动画
        //    popupWindow.setAnimationStyle(R.style.AnimDown);
//设置是否允许PopupWindow的范围超过屏幕范围
        popupWindow.setClippingEnabled(true);
//设置PopupWindow消失监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

        //通过pop对象获取view视图对其设置测量模式
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        //获得view视图的测量宽度
        //左边
        int measuredWidth = popupWindow.getContentView().getMeasuredWidth();
        // popupWindow.showAsDropDown(targetView, -measuredWidth, -targetView.getHeight());
        //按钮的上方
        int measuredHeight = popupWindow.getContentView().getMeasuredHeight();
        popupWindow.showAsDropDown(targetView, 0, -(targetView.getHeight() + measuredHeight));
    }





}

class ToggleSwitch extends Switch {
    public ToggleSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
