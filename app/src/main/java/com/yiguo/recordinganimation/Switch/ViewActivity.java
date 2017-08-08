package com.yiguo.recordinganimation.Switch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yiguo.recordinganimation.R;

public class ViewActivity extends AppCompatActivity {

    private Button collapseView;
    private Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        collapseView = (Button) findViewById(R.id.CollapseView);
        b2 = (Button) findViewById(R.id.b2);
        init();
    }

    private void init() {
        findViewById(R.id.b4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data", "www.baidu.com");
                setResult(111, intent);
                finish();//需要finish()才能触发上个界面

            }
        });
        findViewById(R.id.vViewlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this,MeasureActivity.class));
            }
        });
        findViewById(R.id.vViewlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this,MeasureActivity.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this,MeasureActivity.class));

            }
        });
        collapseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewActivity.this,CollapseViewActivity.class));
            }
        });
    }
}
