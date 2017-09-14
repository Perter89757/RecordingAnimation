package com.yiguo.recordinganimation.callback;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yiguo.recordinganimation.R;
import com.yiguo.recordinganimation.Switch.ViewActivity;
import com.yiguo.recordinganimation.eventListener.DoorListener1;
import com.yiguo.recordinganimation.eventListener.DoorListener2;
import com.yiguo.recordinganimation.eventListener.DoorManager;

public class CallBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_back);
        DoorManager manager = new DoorManager();
        manager.addListener(new DoorListener1());// 给门1增加监听器
        manager.addListener(new DoorListener2());// 给门2增加监听器
        // 开门
        manager.open();
        // 关门
        manager.close();

        startActivityForResult(new Intent(CallBackActivity.this,ViewActivity.class),112);

        findViewById(R.id.select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data", "www");
                setResult(215, intent);
                finish();//需要finish()才能触发上个界面
                // startActivity(new Intent(CallBackActivity.this, MainActivity.class));//没有触发onAcitivyResult();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 111){
            String data1 = data.getStringExtra("data");
            Toast.makeText(this, "返回数据"+data1, Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(CallBackActivity.this,MainActivity.class));
        }
    }
}
