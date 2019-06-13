package com.yiguo.recordinganimation.View;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yiguo.recordinganimation.R;

public class RecordAudioViewActivity extends AppCompatActivity {

    private RecordView recordAudioView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_audio_view);
        recordAudioView = (RecordView) findViewById(R.id.recordAudioView);
        recordAudioView.setOnCountDownListener(new RecordView.OnCountDownListener() {
            @Override
            public void onCountDown() {

                Toast.makeText(RecordAudioViewActivity.this, "录音结束", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void changeType(View view) {
        int model = recordAudioView.getModel();
        if (model == RecordView.MODEL_PLAY) {
            recordAudioView.setModel(RecordView.MODEL_RECORD);
        } else {
            recordAudioView.setModel(RecordView.MODEL_PLAY);
        }
    }

    public void speak(View view) {
        recordAudioView.setCountdownTime(10);
        recordAudioView.start();
     }
}
