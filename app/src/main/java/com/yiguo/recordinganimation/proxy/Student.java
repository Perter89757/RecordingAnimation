package com.yiguo.recordinganimation.proxy;

/**
 * Created by huang_yanhui on 2018/12/17.
 */
public class Student implements studentInfo {
    @Override
    public void play() {
        System.out.println("我学习，");
    }

    @Override
    public void love() {
        System.out.println("我谈恋爱");
    }

    @Override
    public void smoke() {
        System.out.println("我抽烟");
    }

}
