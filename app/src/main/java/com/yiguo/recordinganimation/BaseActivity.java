package com.yiguo.recordinganimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yiguo.recordinganimation.exception.AppStatusConstant;
import com.yiguo.recordinganimation.exception.AppStatusManager;

/**
 * author: huang_yanhui
 * data:2017/9/19
 * time:20:25
 * emaill:huangyh@thinkive.com
 * description:
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (AppStatusManager.getInstance().getAppStatus()) {
            case AppStatusConstant.STATUS_FORCE_KILLED://被杀死
                restartApp();
                break;
            case AppStatusConstant.STATUS_NORMAL://正常状态
                setUpViewAndData();
                break;
        }
    }

    protected abstract void setUpViewAndData();

    protected void restartApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(AppStatusConstant.KEY_HOME_ACTION, AppStatusConstant.ACTION_RESTART_APP);
        startActivity(intent);
    }
}
