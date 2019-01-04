package com.yiguo.recordinganimation.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yiguo.recordinganimation.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONActivity extends AppCompatActivity {
    //要解析的对象
    String json="{\n" +
            "    \"count\": 2,\n" +
            "    \"start\": 0,\n" +
            "    \"total\": 250,\n" +
            "    \"subjects\": [\n" +
            "        {\n" +
            "            \"rating\": {\n" +
            "                \"max\": 10,\n" +
            "                \"average\": 9.6,\n" +
            "                \"stars\": \"50\",\n" +
            "                \"min\": 0\n" +
            "            },\n" +
            "            \"genres\": [\n" +
            "                \"犯罪\",\n" +
            "                \"剧情\"\n" +
            "            ],\n" +
            "            \"title\": \"肖申克的救赎\",\n" +
            "            \"casts\": [\n" +
            "                {\n" +
            "                    \"alt\": \"https://movie.douban.com/celebrity/1054521/\",\n" +
            "                    \"avatars\": {\n" +
            "                        \"small\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp\",\n" +
            "                        \"large\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp\",\n" +
            "                        \"medium\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17525.webp\"\n" +
            "                    },\n" +
            "                    \"name\": \"蒂姆·罗宾斯\",\n" +
            "                    \"id\": \"1054521\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"alt\": \"https://movie.douban.com/celebrity/1054534/\",\n" +
            "                    \"avatars\": {\n" +
            "                        \"small\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34642.webp\",\n" +
            "                        \"large\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34642.webp\",\n" +
            "                        \"medium\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34642.webp\"\n" +
            "                    },\n" +
            "                    \"name\": \"摩根·弗里曼\",\n" +
            "                    \"id\": \"1054534\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"alt\": \"https://movie.douban.com/celebrity/1041179/\",\n" +
            "                    \"avatars\": {\n" +
            "                        \"small\": \"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5837.webp\",\n" +
            "                        \"large\": \"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5837.webp\",\n" +
            "                        \"medium\": \"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5837.webp\"\n" +
            "                    },\n" +
            "                    \"name\": \"鲍勃·冈顿\",\n" +
            "                    \"id\": \"1041179\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"collect_count\": 1241931,\n" +
            "            \"original_title\": \"The Shawshank Redemption\",\n" +
            "            \"subtype\": \"movie\",\n" +
            "            \"directors\": [\n" +
            "                {\n" +
            "                    \"alt\": \"https://movie.douban.com/celebrity/1047973/\",\n" +
            "                    \"avatars\": {\n" +
            "                        \"small\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.webp\",\n" +
            "                        \"large\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.webp\",\n" +
            "                        \"medium\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p230.webp\"\n" +
            "                    },\n" +
            "                    \"name\": \"弗兰克·德拉邦特\",\n" +
            "                    \"id\": \"1047973\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"year\": \"1994\",\n" +
            "            \"images\": {\n" +
            "                \"small\": \"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp\",\n" +
            "                \"large\": \"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp\",\n" +
            "                \"medium\": \"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p480747492.webp\"\n" +
            "            },\n" +
            "            \"alt\": \"https://movie.douban.com/subject/1292052/\",\n" +
            "            \"id\": \"1292052\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"rating\": {\n" +
            "                \"max\": 10,\n" +
            "                \"average\": 9.5,\n" +
            "                \"stars\": \"50\",\n" +
            "                \"min\": 0\n" +
            "            },\n" +
            "            \"genres\": [\n" +
            "                \"剧情\",\n" +
            "                \"爱情\",\n" +
            "                \"同性\"\n" +
            "            ],\n" +
            "            \"title\": \"霸王别姬\",\n" +
            "            \"casts\": [\n" +
            "                {\n" +
            "                    \"alt\": \"https://movie.douban.com/celebrity/1003494/\",\n" +
            "                    \"avatars\": {\n" +
            "                        \"small\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5515.webp\",\n" +
            "                        \"large\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5515.webp\",\n" +
            "                        \"medium\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5515.webp\"\n" +
            "                    },\n" +
            "                    \"name\": \"张国荣\",\n" +
            "                    \"id\": \"1003494\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"alt\": \"https://movie.douban.com/celebrity/1050265/\",\n" +
            "                    \"avatars\": {\n" +
            "                        \"small\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46345.webp\",\n" +
            "                        \"large\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46345.webp\",\n" +
            "                        \"medium\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46345.webp\"\n" +
            "                    },\n" +
            "                    \"name\": \"张丰毅\",\n" +
            "                    \"id\": \"1050265\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"alt\": \"https://movie.douban.com/celebrity/1035641/\",\n" +
            "                    \"avatars\": {\n" +
            "                        \"small\": \"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1399268395.47.webp\",\n" +
            "                        \"large\": \"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1399268395.47.webp\",\n" +
            "                        \"medium\": \"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1399268395.47.webp\"\n" +
            "                    },\n" +
            "                    \"name\": \"巩俐\",\n" +
            "                    \"id\": \"1035641\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"collect_count\": 895251,\n" +
            "            \"original_title\": \"霸王别姬\",\n" +
            "            \"subtype\": \"movie\",\n" +
            "            \"directors\": [\n" +
            "                {\n" +
            "                    \"alt\": \"https://movie.douban.com/celebrity/1023040/\",\n" +
            "                    \"avatars\": {\n" +
            "                        \"small\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1451727734.81.webp\",\n" +
            "                        \"large\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1451727734.81.webp\",\n" +
            "                        \"medium\": \"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1451727734.81.webp\"\n" +
            "                    },\n" +
            "                    \"name\": \"陈凯歌\",\n" +
            "                    \"id\": \"1023040\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"year\": \"1993\",\n" +
            "            \"images\": {\n" +
            "                \"small\": \"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1910813120.webp\",\n" +
            "                \"large\": \"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1910813120.webp\",\n" +
            "                \"medium\": \"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p1910813120.webp\"\n" +
            "            },\n" +
            "            \"alt\": \"https://movie.douban.com/subject/1291546/\",\n" +
            "            \"id\": \"1291546\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"title\": \"豆瓣电影Top250\"\n" +
            "}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        try {
            JSONObject rootData = new JSONObject(json);
            JSONArray subjects = rootData.getJSONArray("subjects");
            JSONObject jsonObject1 = subjects.getJSONObject(0);
            JSONArray casts = jsonObject1.getJSONArray("casts");
            JSONObject casts0 = casts.getJSONObject(0);
            JSONObject avatars = casts0.getJSONObject("avatars");
            String small = avatars.getString("small");
            Log.d("JSON",small);
        } catch (JSONException e) {


        }

    }
}
