package com.yiguo.recordinganimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.yiguo.recordinganimation.utils.EmojiUtils;

public class EmojiActivity extends AppCompatActivity {

    private EditText etInput;
    private TextView tv_showEmoji;
    private Button getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);
        etInput = (EditText) findViewById(R.id.input);
        tv_showEmoji = (TextView) findViewById(R.id.showEmoji);
        getData = (Button) findViewById(R.id.getText);

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etInput.getText().toString();
                Spannable spannable = EmojiUtils.getSmiledText(EmojiActivity.this,s,0);
                tv_showEmoji.setText(spannable);
            }
        });


    }
}
