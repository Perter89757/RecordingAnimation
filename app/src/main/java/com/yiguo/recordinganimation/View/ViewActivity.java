package com.yiguo.recordinganimation.View;

import android.content.Intent;
import android.net.SSLCertificateSocketFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yiguo.recordinganimation.R;
import com.yiguo.recordinganimation.View.TouchEvent.TouchEventActivity;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class ViewActivity extends AppCompatActivity {

    private Button collapseView;
    private Button b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        init();
    }

    private void init() {
        findViewById(R.id.vlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, VLayoutActivity.class));

            }
        });
        findViewById(R.id.slideLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, SlideLayoutActivity.class));

            }
        });
        findViewById(R.id.b7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, SpanTextViewActivity.class));
            }
        });
        findViewById(R.id.b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, Circle2Activity.class));
            }
        });

        findViewById(R.id.B4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, Se4CodeActivity.class));
            }
        });
        findViewById(R.id.b5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, RaderActivity.class));
            }
        });
        findViewById(R.id.b6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this, TouchEventActivity.class));
            }
        });

    }

    public void RoundView(View view) {
        startActivity(new Intent(ViewActivity.this, RoundViewActivity.class));
    }

    public void recordAudio(View view) {
        startActivity(new Intent(ViewActivity.this, RecordAudioViewActivity.class));

    }
}
