package com.yiguo.recordinganimation.Service.loadServiceData.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * author: huang_yanhui
 * data:2017/9/6
 * time:15:37
 * emaill:huangyh@thinkive.com
 * description:
 */

public class DouBanMovie implements Parcelable {
    @Override
    public String toString() {
        return "DouBanMovie{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    /**
     * count : 2
     * start : 0
     * total : 250
     * subjects : [{"rating":{"max":10,"average":9.6,"stars":"50","min":0},"genres":["犯罪","剧情"],"title":"肖申克的救赎","casts":[{"alt":"https://movie.douban.com/celebrity/1054521/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/17525.jpg","large":"https://img3.doubanio.com/img/celebrity/large/17525.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/17525.jpg"},"name":"蒂姆·罗宾斯","id":"1054521"},{"alt":"https://movie.douban.com/celebrity/1054534/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/34642.jpg","large":"https://img3.doubanio.com/img/celebrity/large/34642.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/34642.jpg"},"name":"摩根·弗里曼","id":"1054534"},{"alt":"https://movie.douban.com/celebrity/1041179/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/5837.jpg","large":"https://img1.doubanio.com/img/celebrity/large/5837.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/5837.jpg"},"name":"鲍勃·冈顿","id":"1041179"}],"collect_count":1116141,"original_title":"The Shawshank Redemption","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1047973/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/230.jpg","large":"https://img3.doubanio.com/img/celebrity/large/230.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/230.jpg"},"name":"弗兰克·德拉邦特","id":"1047973"}],"year":"1994","images":{"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.webp","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.webp","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p480747492.webp"},"alt":"https://movie.douban.com/subject/1292052/","id":"1292052"},{"rating":{"max":10,"average":9.5,"stars":"50","min":0},"genres":["剧情","爱情","同性"],"title":"霸王别姬","casts":[{"alt":"https://movie.douban.com/celebrity/1003494/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/67.jpg","large":"https://img1.doubanio.com/img/celebrity/large/67.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/67.jpg"},"name":"张国荣","id":"1003494"},{"alt":"https://movie.douban.com/celebrity/1050265/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/46345.jpg","large":"https://img3.doubanio.com/img/celebrity/large/46345.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/46345.jpg"},"name":"张丰毅","id":"1050265"},{"alt":"https://movie.douban.com/celebrity/1035641/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/1399268395.47.jpg","large":"https://img1.doubanio.com/img/celebrity/large/1399268395.47.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/1399268395.47.jpg"},"name":"巩俐","id":"1035641"}],"collect_count":796317,"original_title":"霸王别姬","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1023040/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/750.jpg","large":"https://img3.doubanio.com/img/celebrity/large/750.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/750.jpg"},"name":"陈凯歌","id":"1023040"}],"year":"1993","images":{"small":"https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p1910813120.webp","large":"https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p1910813120.webp","medium":"https://img3.doubanio.com/view/movie_poster_cover/spst/public/p1910813120.webp"},"alt":"https://movie.douban.com/subject/1291546/","id":"1291546"}]
     * title : 豆瓣电影Top250
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private java.util.List<SubjectsBean> subjects;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeInt(this.start);
        dest.writeInt(this.total);
        dest.writeString(this.title);
        dest.writeTypedList(this.subjects);
    }

    public DouBanMovie() {
    }

    protected DouBanMovie(Parcel in) {
        this.count = in.readInt();
        this.start = in.readInt();
        this.total = in.readInt();
        this.title = in.readString();
        this.subjects = in.createTypedArrayList(SubjectsBean.CREATOR);
    }

    public static final Parcelable.Creator<DouBanMovie> CREATOR = new Parcelable.Creator<DouBanMovie>() {
        @Override
        public DouBanMovie createFromParcel(Parcel source) {
            return new DouBanMovie(source);
        }

        @Override
        public DouBanMovie[] newArray(int size) {
            return new DouBanMovie[size];
        }
    };
}
