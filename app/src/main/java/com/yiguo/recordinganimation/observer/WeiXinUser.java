package com.yiguo.recordinganimation.observer;

/**
 * author: huang_yanhui
 * data:2017/7/31
 * time:10:43
 * emaill:huangyh@thinkive.com
 * description:
 */

public class WeiXinUser implements observer {
    private String userName;

    public WeiXinUser(String userName) {
        this.userName = userName;
    }

    @Override
    public void updata() {
        System.out.println(userName+"你关注的人更新了");
    }
}
