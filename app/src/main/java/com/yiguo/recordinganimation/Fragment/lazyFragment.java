package com.yiguo.recordinganimation.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: huang_yanhui
 * data:2017/10/23
 * time:14:09
 * emaill:huangyh@thinkive.com
 * description:
 */

public abstract class lazyFragment extends Fragment {

    private View rootView;
    private boolean mIsPrepared;
    private boolean mIsInited;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayout(), container, false);
        mIsPrepared = true;//视图已经初始化好
        Log.d("Fragment", "加载UI");
        lazyData();
        return rootView;
    }

    public void lazyData() {
        //满足三个条件,才加载数据
        if (getUserVisibleHint() && mIsPrepared && !mIsInited) {
            //加载数据
            loadData();
            mIsInited = true;//已加载过数据,当再次切换回来不在请求新的数据
            Log.d("Fragment", "满足条件-lazyData");

        } else {
            Log.d("Fragment","不满足,不加载lazyData");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyData();
        }
    }

    public abstract int setLayout();

    public abstract void loadData();
}
