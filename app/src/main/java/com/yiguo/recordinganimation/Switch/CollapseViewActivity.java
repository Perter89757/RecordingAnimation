package com.yiguo.recordinganimation.Switch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yiguo.recordinganimation.R;

public class CollapseViewActivity extends AppCompatActivity {

    private CollapseView collapse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapse_view);
        collapse = (CollapseView) findViewById(R.id.Collapse);
        collapse.setContent(R.layout.listview_pop);

    }
}
