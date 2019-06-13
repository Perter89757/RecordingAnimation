package com.yiguo.recordinganimation.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.yiguo.recordinganimation.R;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {
    @Inject
     User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        initData();
    }

    private void initData() {

    }
}
