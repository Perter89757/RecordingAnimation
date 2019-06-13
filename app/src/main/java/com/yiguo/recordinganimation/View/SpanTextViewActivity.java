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

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.yiguo.recordinganimation.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpanTextViewActivity extends AppCompatActivity {

    private TextView tvSpanView;
    private TextView tvSpanView2;
    private ExpandableTextView expandableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span_text_view);
        tvSpanView = (TextView) findViewById(R.id.tvSpan);
        tvSpanView2 = (TextView) findViewById(R.id.tvSpan2);
        expandableTextView = (ExpandableTextView) findViewById(R.id.ep_01);

        showcolorchar();

        testColoredKeywd();

        String originalText = "我是思迪客服，很高兴能为您提供咨询服务，有问题可以直接问我哟！<br/><a href=\"id=04cea30c74b846ada4de34b85d8a9478,sourceType=1,title=1.什么情况下，交易所会提高交易保证金？\">1.什么情况下，交易所会提高交易保证金？</a><br/><a href=\"id=175e105abf024e25ad695f605926c67b,sourceType=1,title=2.开户信息\">2.开户信息</a><br/><a href=\"id=21af3a69e9f84c22bb43a33dd04f5773,sourceType=1,title=3.目前国际上原油现货和期货交易主要集中在哪些国家和地区？\">3.目前国际上原油现货和期货交易主要集中在哪些国家和地区？</a><br/> \n" +
                "<br/>--------------------------------<br/>您也可以点击 <a href=\"id=11001,sourceType=human,title=转人工客服\"}\">转人工客服</a>";
        //转化之前的文字
         Spanned spanned;
        //提取其中的超链接
          spanned = Html.fromHtml(originalText);
         //转化之后的文字
        String afterTransfer = spanned.toString();
        expandableTextView.setContent(afterTransfer);
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
