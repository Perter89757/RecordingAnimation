package com.yiguo.recordinganimation.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yiguo.recordinganimation.R;

import java.util.ArrayList;

public class Se4CodeActivity extends AppCompatActivity {

    private TextView TextViews3;
    private TextView TextViews0;
    private TextView TextViews1;
    private TextView TextViews2;
    private EditText editText;
    private ArrayList<TextView> textViews;
    private StringBuilder stringBuilder;
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_se4_code);
        textViews = new ArrayList<>();
        stringBuilder = new StringBuilder();

        TextViews0 = (TextView) findViewById(R.id.item_code_iv1);
        TextViews1 = (TextView) findViewById(R.id.item_code_iv2);
        TextViews2 = (TextView) findViewById(R.id.item_code_iv3);
        TextViews3 = (TextView) findViewById(R.id.item_code_iv4);
        textViews.add(TextViews0);
        textViews.add(TextViews1);
        textViews.add(TextViews2);
        textViews.add(TextViews3);

        editText = (EditText) findViewById(R.id.item_edittext);
        initListener();
    }

    private void initListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String code = s.toString().trim();
                if (!TextUtils.isEmpty(code) && count <= 3) {
                    stringBuilder.append(code);
                    count = stringBuilder.length();
                    textViews.get(count-1).setText(code);
                    Log.d("TAG", "输出" + stringBuilder.toString());
                    editText.setText("");
                    if (count == 4) {
                        if ("1234".equals(stringBuilder.toString())) {
                            Toast.makeText(Se4CodeActivity.this, "验证通过", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Se4CodeActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (count > 0) {
                        stringBuilder.delete(count-1,count);
                        textViews.get(count-1).setText("");
                        count = stringBuilder.length();
                        return true;
                    } else return false;

                }
                return false;
            }
        });
    }

}
