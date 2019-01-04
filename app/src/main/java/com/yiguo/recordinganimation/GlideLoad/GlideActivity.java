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
                startActivity(new Intent(GlideActivity.this,ListImageLoadActivity.class));
            }
        });
        final String url = "http://img.blog.csdn.net/20160507110203928";
        String gif_url = "http://p1.pstatp.com/large/166200019850062839d3";
         Glide.with(this).load(url).placeholder(R.drawable.emoji_1).error(R.drawable.ic_launcher).into(imageShow);
//        Glide.with(this)
//                .load(url)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .preload();
//        LoaderOptions options = new LoaderOptions();
//        options.setPath(url);
//        options.setErrorID(R.drawable.bj);
//        options.setPlacehholer(R.drawable.checkbox_iphone);
//        options.setImageView(imageShow);
//        GlideLoadEngine.getInstance(this).LoadImage(options,null);

        // imageLoadUtils.getInstance().loadurl(url).errorPic(R.drawable.bj).into(imageShow);
//        imageShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Glide.with(GlideActivity.this)
//                        .load(url)
//                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                        .dontTransform()
//                        .into(imageShow);
//            }
//        });
        String html = "<html><head><title>TextView使用HTML</title></head><body>"
                + "<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p>"
                + "下面是网络图片</p><img src=\"https://img.alicdn.com/imgextra/i3/2454452051/TB2DH1acYFkpuFjy1XcXXclapXa_!!2454452051.jpg\"/></body></html>"
                + "下面是网络图片</p><img src=\"http://avatar.csdn.net/0/3/8/2_zhang957411207.jpg\"/></body></html>";
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置可滚动
        tv.setMovementMethod(LinkMovementMethod.getInstance());//设置超链接可以打开网页
        NetworkImageGetter imageGetter = new NetworkImageGetter();
        tv.setText(Html.fromHtml(html, imageGetter, null));
    }

    private final class NetworkImageGetter implements Html.ImageGetter {

        @Override
        public Drawable getDrawable(String source) {
            // TODO Auto-generated method stub

            LevelListDrawable d = new LevelListDrawable();
            new LoadImage().execute(source, d);
            return d;
        }

    }

    /**** 异步加载图片 **/
    private final class LoadImage extends AsyncTask<Object, Void, Bitmap> {

        private LevelListDrawable mDrawable;

        @Override
        protected Bitmap doInBackground(Object... params) {
            String source = (String) params[0];
            mDrawable = (LevelListDrawable) params[1];
            try {
                InputStream is = new URL(source).openStream();
                return BitmapFactory.decodeStream(is);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                BitmapDrawable d = new BitmapDrawable(bitmap);
                mDrawable.addLevel(1, 1, d);
                mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);
                CharSequence t = tv.getText();
                tv.setText(t);
            }
        }
    }

}
