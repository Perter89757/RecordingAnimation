package com.yiguo.recordinganimation.dagger;

import javax.inject.Inject;

/**
 * author: huang_yanhui
 * data:2017/10/10
 * time:14:46
 * emaill:huangyh@thinkive.com
 * description:
 */

public class User {
    private String name;
    private String password;

    //@Inject
    public User(String name,PassWord passWord) {
        this.name = name;
        this.password = passWord.getPassword();
    }
    @Inject
    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name + "密码:"+password;
    }

}
