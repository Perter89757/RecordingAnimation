package com.yiguo.recordinganimation.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.widget.TextView;

import com.yiguo.recordinganimation.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpanTextViewActivity extends AppCompatActivity {

    private TextView tvSpanView;
    private TextView tvSpanView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span_text_view);
        tvSpanView = (TextView) findViewById(R.id.tvSpan);
        tvSpanView2 = (TextView) findViewById(R.id.tvSpan2);

        showcolorchar();

        testColoredKeywd();



    }

    /**
     * 普通文本 变色
     */
    private void testColoredKeywd() {
        String string = "Android一词的本义指“机器人”，同时也是Google于2007年11月5日,Android logo相关图片,Android logo相关图片(36张)\n";
        SpannableString spannableString = new SpannableString(string);
        Pattern patten = Pattern.compile("Android", Pattern.CASE_INSENSITIVE);//将启动对ASCII字符不区分大小写匹配
        Matcher matcher = patten.matcher(spannableString);
        int  start = 0;
        //matcher.start() 每次都会自动变化数字到关键字开头的字符
        //matcher.group ()  整个关键字
        //matcher.group(0) 关键字中的第一个字符
        //为字体设置颜色
        //为字体设置点击事件
        while (matcher.find()) {
            final String key = matcher.group();
            if (matcher.start() < start) {
                continue;
            }
            int end = matcher.start() + key.length();

            //
//            spannableString.setSpan(new ClickableSpan() {
//                @Override
//                public void onClick(View widget) {
//                  //  Toast.makeText(SpanTextViewActivity.this, "点击了"+key, Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void updateDrawState(TextPaint ds) {
//                    super.updateDrawState(ds);
//                    ds.setUnderlineText(false);
//                }
//            },matcher.start(),end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            //ForegroundColorSpan 文本颜色（前景色）
            int color =getResources().getColor(R.color.TimeTextColor);
            spannableString.setSpan(new ForegroundColorSpan(color),matcher.start(),end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            //跳转链接
            spannableString.setSpan(new URLSpan("www.baidu.com"),matcher.start(),end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            if (end >= spannableString.length()) {
                break;
            }
        }

        tvSpanView2.setText(spannableString);
        //如果想实现点击，必须要设置这个
        tvSpanView2.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /**
     * 标签文本 变色
     */
    private void showcolorchar() {
        String s = "今日已有<font color=\"#f0717e\">1</font>人签到，日榜单排在第<font color=\"#f0717e\">1</font>名";
        Spanned spanned = Html.fromHtml(s);
        tvSpanView.setText(spanned);
    }


}
