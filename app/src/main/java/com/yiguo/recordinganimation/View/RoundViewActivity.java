package com.yiguo.recordinganimation.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yiguo.recordinganimation.R;

public class RoundViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_view);
        ImageView roundView = (ImageView) findViewById(R.id.roundView);
       Picasso.with(this).load("https://pic2.zhimg.com/v2-ea22f13cd5357a6829a090e7e658bb26_b.jpg").into(roundView);
    }


}
