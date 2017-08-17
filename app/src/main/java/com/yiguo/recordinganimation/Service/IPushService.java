package com.yiguo.recordinganimation.Service;

/*
 *  @项目名：  RecordingAnimation 
 *  @包名：    com.yiguo.recordinganimation.Service
 *  @文件名:   IPushService
 *  @创建者:   HuangYanHui
 *  @创建时间:  2017/8/17 0017 21:55
 *  @描述：    TODO
 */

import com.yiguo.recordinganimation.callback.CallBack;

public interface IPushService {
    void login(int userName ,CallBack callBack);
}
