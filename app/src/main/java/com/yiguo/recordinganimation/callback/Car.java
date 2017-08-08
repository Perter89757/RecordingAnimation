package com.yiguo.recordinganimation.callback;

/**
 * author: huang_yanhui
 * data:2017/6/13
 * time:16:50
 * emaill:huangyh@thinkive.com
 * description:
 */

public class Car {
    public interface onClickListeren{
        void onclick();
    }
    public interface CarCallBack{
        void oncallback(String s);
    }
    private CarCallBack callBack;
    public void setCallBack(CarCallBack call){
        callBack = call;

    }
    public void callCar(String s){
        String s1 = s+"ssaf";
        callBack.oncallback(s1);
    }
    public onClickListeren onclickListeren;
    public void setonclickListeren(onClickListeren listeren){
        onclickListeren = listeren;
    }

    public void cfangfa(){
        onclickListeren.onclick();
    }
}
