package com.yiguo.recordinganimation.dagger;

/**
 * author: huang_yanhui
 * data:2017/10/10
 * time:16:11
 * emaill:huangyh@thinkive.com
 * description:
 */

public class PassWord {
    public PassWord(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;
}
