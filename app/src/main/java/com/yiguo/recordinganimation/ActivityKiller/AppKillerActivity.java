package com.yiguo.recordinganimation.ActivityKiller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yiguo.recordinganimation.R;

public class AppKillerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_killer);
        EditNameDialogFragment dialogFragment = new EditNameDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "t1");
    }
}
