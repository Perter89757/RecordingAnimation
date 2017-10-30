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
       // DaggerUserComonpent.builder().userModule(new UserModule("jack")).build().inject(this);
        DaggerUserComonpent.builder().userModule(new UserModule()).build().inject(this);
        initData();
    }

    private void initData() {
        // user = new User();
      //  user.setName("测试");
        Log.d("dagger",user.getName());
    }
}
