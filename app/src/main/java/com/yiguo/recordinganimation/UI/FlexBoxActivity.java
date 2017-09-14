package com.yiguo.recordinganimation.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.yiguo.recordinganimation.R;

public class FlexBoxActivity extends AppCompatActivity {

    private FlexboxLayout mFlexboxLayout;
    private LinearLayout rootLayout;
    private LinearLayout viewLayout;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex_box);
        mFlexboxLayout = (FlexboxLayout) findViewById(R.id.flexboxLayout);
        rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        viewLayout = (LinearLayout) findViewById(R.id.viewLayout);


        for (int i = 0; i < 10; i++) {
            TextView textView = new TextView(this);
            textView.setText("Test  Label");
            FlexboxLayout.MarginLayoutParams marginLayoutParams = new FlexboxLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            marginLayoutParams.setMargins(20, 20, 20, 0);
            textView.setLayoutParams(marginLayoutParams);
            //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
           // layoutParams.setMargins(60, 20, 60, 0);
           // textView.setLayoutParams(layoutParams);
            textView.setPadding(10, 0, 10, 0);
            textView.setBackgroundColor(getResources().getColor(R.color.RoundColor));
            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            mFlexboxLayout.addView(textView);
            //  viewLayout.addView(textView);

        }


    }
}
