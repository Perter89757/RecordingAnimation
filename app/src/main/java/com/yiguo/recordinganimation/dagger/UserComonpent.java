package com.yiguo.recordinganimation.dagger;

import dagger.Component;

/**
 * author: huang_yanhui
 * data:2017/10/10
 * time:14:52
 * emaill:huangyh@thinkive.com
 * description:
 */
@Component(modules = UserModule.class)
public interface UserComonpent {

    void inject(DaggerActivity daggerActivity);
}
