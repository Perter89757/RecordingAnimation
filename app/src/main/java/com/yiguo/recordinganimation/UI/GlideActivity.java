package com.yiguo.recordinganimation.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yiguo.recordinganimation.R;

public class GlideActivity extends AppCompatActivity {

    private ImageView imageShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        imageShow = (ImageView) findViewById(R.id.ivshow);
        final String url = "http://img.blog.csdn.net/20160507110203928";
        String gif_url = "http://p1.pstatp.com/large/166200019850062839d3";
       // Glide.with(this).load(url).placeholder(R.drawable.emoji_1).error(R.drawable.ic_launcher).into(imageShow);
        Glide.with(this)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .preload();

        imageShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(GlideActivity.this)
                        .load(url)
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .dontTransform()
                        .into(imageShow);
            }
        });
    }
}
