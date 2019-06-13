package com.yiguo.recordinganimation.GlideLoad;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yiguo.recordinganimation.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class GlideActivity extends AppCompatActivity {

    private ImageView imageShow;
    private TextView tv;
    private Button loadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        imageShow = (ImageView) findViewById(R.id.ivshow);
        tv = (TextView) findViewById(R.id.htmlText);
        loadImage = (Button) findViewById(R.id.loadImage);
        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GlideActivity.this, ListImageLoadActivity.class));
            }
        });
        final String url = "http://img.blog.csdn.net/20160507110203928";
        String gif_url = "http://p1.pstatp.com/large/166200019850062839d3";
        Glide.with(this).load(url).placeholder(R.drawable.emoji_1).error(R.drawable.ic_launcher).into(imageShow);

    }


}
