package com.yiguo.recordinganimation.Switch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewConfiguration;

import com.yiguo.recordinganimation.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SwitchActivity extends AppCompatActivity {

    private static WeakReference<Activity> weakActivity = null;

    // 自定义Lv
    private CustomListView mCustomLv;
    // 自定义适配器
    private CustomListViewAdapter mAdapter;
    // 内容列表
    private List<String> contentList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        initContentList();
        mCustomLv = (CustomListView) findViewById(R.id.custom_lv);
        mCustomLv.setOnDeleteListener(new CustomListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                contentList.remove(index);
                mAdapter.notifyDataSetChanged();
            }
        });

        mAdapter = new CustomListViewAdapter(this, 0, contentList);
        mCustomLv.setAdapter(mAdapter);


        //双击间隔时间.在该时间内是双击，否则是单击
        int doubleTapTimeout = ViewConfiguration.getDoubleTapTimeout();
        Log.d("TAG","双击间隔时间:"+doubleTapTimeout);
//按住状态转变为长按状态需要的时间
        int longPressTimeout = ViewConfiguration.getLongPressTimeout();
        Log.d("TAG","长按:"+longPressTimeout);
//重复按键的时间
        int keyRepeatTimeout = ViewConfiguration.getKeyRepeatTimeout();
        Log.d("TAG","重复按键的时间:"+keyRepeatTimeout);

    }

    private void initContentList() {
        for (int i = 0; i < 20; i++) {
            contentList.add("内容项" + i);
        }
    }


    private static Runnable MyRunable = new Runnable() {
        @Override
        public void run() {
            //dosometing
        }
    };

    private static class MyHandler extends Handler {
        public MyHandler(Activity activity) {
            weakActivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (weakActivity == null) return;
            //doSomething
        }
    }

    class handler2 extends Handler {
        @Override
        public void handleMessage(Message msg) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mCustomLv.isDeleteShown()) {
            mCustomLv.hideDelete();
            return;
        }
        super.onBackPressed();
    }
}
