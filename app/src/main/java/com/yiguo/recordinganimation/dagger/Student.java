package com.yiguo.recordinganimation.dagger;

/**
 * Created by huang_yanhui on 2018/11/28.
 */

public class Student {
    public Student(String name) {
        this.name = name;
    }
    public Student() {

    }

    //**********字段*************//
    public String name;
    protected int age;
    char sex;
    private String phoneNum;

    private static void show(String name) {
        System.out.println("调用了：公有的，String参数的show1(): s = " + name);
    }
}
