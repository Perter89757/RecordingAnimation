package com.yiguo.recordinganimation.dagger;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by huang_yanhui on 2019/4/30.
 */
@Target(ElementType.METHOD)//注解对象的作用范围
@Retention(RetentionPolicy.RUNTIME)//注解保留的生命周期,运行时才生效
 public @interface Secure {

}
