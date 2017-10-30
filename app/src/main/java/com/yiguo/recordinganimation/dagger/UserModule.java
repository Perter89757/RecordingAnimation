package com.yiguo.recordinganimation.dagger;

import dagger.Module;
import dagger.Provides;

/**
 * author: huang_yanhui
 * data:2017/10/10
 * time:14:51
 * emaill:huangyh@thinkive.com
 * description:
 */
@Module
public class UserModule {
    String name;
//    public UserModule(String name) {
//        this.name = name;
//    }

    @Provides
    public User provideUser() {
        return new User("jack",new PassWord("123456"));
    }
}
