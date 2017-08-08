package com.yiguo.recordinganimation.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.util.Log;

import com.yiguo.recordinganimation.R;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: huang_yanhui
 * data:2017/7/3
 * time:11:10
 * emaill:huangyh@thinkive.com
 * description:
 */

public class EmojiUtils {
    //表示一个表情的字符串
    public static final String emoji_1 = "[@emoji_1.gif]";
    public static final String emoji_2 = "[@emoji_2.gif]";
    public static final String emoji_3 = "[@emoji_3.gif]";



    private static final Map<Pattern, Integer> emoticons = new HashMap<Pattern, Integer>();

    //
    static {
        addPattern(emoticons, emoji_1, R.drawable.emoji_1);
        addPattern(emoticons, emoji_2, R.drawable.emoji_2);
        addPattern(emoticons, emoji_3, R.drawable.emoji_3);
    }

    //字符串和表情资源建立连接
    private static void addPattern(Map<Pattern, Integer> map, String smile, int resource) {
        Log.d("TAG", "Pattern.quote(smile)=" + Pattern.quote(smile));
        map.put(Pattern.compile(Pattern.quote(smile)), resource);
    }

    private static final Spannable.Factory spannableFactory = Spannable.Factory
            .getInstance();

    public static Spannable getSmiledText(Context context, CharSequence text, int size) {
        Spannable spannable = spannableFactory.newSpannable(text);
        addSmiles(context, spannable, size);
        return spannable;
    }

    /**
     * 遍历匹配文本中是否含有表情的字符串
     * @param context
     * @param spannable
     * @param size
     * @return
     */
    public static boolean addSmiles(Context context, Spannable spannable, int size) {
        boolean hasChanges = false;
        for (Map.Entry<Pattern, Integer> entry : emoticons.entrySet()) {
            Matcher matcher = entry.getKey().matcher(spannable);
            while (matcher.find()) {
                boolean set = true;
                for (ImageSpan span : spannable.getSpans(matcher.start(), matcher.end(), ImageSpan.class))
                    if (spannable.getSpanStart(span) >= matcher.start()
                            && spannable.getSpanEnd(span) <= matcher.end())
                        spannable.removeSpan(span);
                    else {
                        set = false;
                        break;
                    }
                if (set) {
                    hasChanges = true;
                    Drawable drawable = context.getResources().getDrawable(entry.getValue());
                    if (size != 0) {
                        drawable.setBounds(0, 0, size, size);
                        spannable.setSpan(new ImageSpan(drawable),
                                matcher.start(), matcher.end(),
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    } else {
                        spannable.setSpan(new ImageSpan(context, entry.getValue()),
                                matcher.start(), matcher.end(),
                                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }

                }
            }
        }
        return hasChanges;
    }
}
