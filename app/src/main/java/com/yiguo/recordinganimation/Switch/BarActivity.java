package com.yiguo.recordinganimation.Switch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.yiguo.recordinganimation.R;

public class BarActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
//                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        setContentView(R.layout.activity_bar);
        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://113.98.239.189:8080/m/mall/views/action/indexEjiayou.html");
       // webView.loadUrl("https://www.baidu.com/s?ie=UTF-8&wd=%22www.baidu.com%22");
       // AndroidBug5497Workaround.assistActivity(this);
    }
}
