package com.yiguo.recordinganimation.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.yiguo.recordinganimation.R;

public class DemoFragmentActivity extends FragmentActivity implements View.OnClickListener {

    private FrameLayout emptyFragment;
    private Button btn1;
    private Button btn2;
    private Button viewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_fragment);
        btn1 =(Button)findViewById(R.id.button1);
        btn2 =(Button)findViewById(R.id.button2);
        viewPage = (Button) findViewById(R.id.ViewPgae);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        viewPage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        switch (v.getId()){
            case R.id.button1:
                oneFragment fragment1=new oneFragment();
                fm.beginTransaction().replace(R.id.my_Frame,fragment1).commit();
                break;
            case R.id.button2:
                twoFragment myFrament2=new twoFragment();
                fm.beginTransaction().replace(R.id.my_Frame,myFrament2).commit();
                break;
            case R.id.ViewPgae:
                Intent intent = new Intent(DemoFragmentActivity.this, LazyFragmentActivity.class);
                startActivity(intent);
                break;
        }
    }
}
