package com.yiguo.recordinganimation.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yiguo.recordinganimation.R;

public class LazyFragmentActivity extends AppCompatActivity {

    private ViewPager viewpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lazy_fragment);
        viewpage = (ViewPager) findViewById(R.id.rootViewPage);
        viewpage.setAdapter(new viewPagerAdapter(getSupportFragmentManager()));
      //  viewpage.setOffscreenPageLimit(3);
    }
}
