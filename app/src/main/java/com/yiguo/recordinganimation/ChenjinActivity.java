package com.yiguo.recordinganimation;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yiguo.recordinganimation.View.DiscolourScrollView;

public class ChenjinActivity extends AppCompatActivity{


    private DiscolourScrollView scrollView;
    private ImageView imageView;
    private Toolbar textView;
    private int imageHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        //1.去除toolBar
        //2.透明状态栏
        setContentView(R.layout.activity_chenjin);
        scrollView = (DiscolourScrollView) findViewById(R.id.scrollview);
        imageView = (ImageView) findViewById(R.id.imageview);
        textView = (Toolbar) findViewById(R.id.textview);
        initListeners();
    }

    private void initListeners() {
        // 获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                imageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                imageHeight = imageView.getHeight();
                //设置自定义接口,监听滑动时xy值的变化
                scrollView.setScrollViewListener(new DiscolourScrollView.ScrollViewListener() {
                    @Override
                    public void onScrollChanged(DiscolourScrollView scrollView, int x, int y, int oldx, int oldy) {
                        if (y <= 0) {//scrollView往上滑动
                            textView.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//这里设置颜色
                        } else if (y > 0 && y <= imageHeight) { //滑动距离小于图片的高度
                            float scale = (float) y / imageHeight;
                            float alpha = (255 * scale);
                            // 只是layout背景透明
                            textView.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                        } else {//
                            textView.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                        }
                    }
                });
            }
        });
    }


}
