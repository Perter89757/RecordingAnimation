package com.yiguo.recordinganimation.Producers;

/**
 * author: huang_yanhui
 * data:2017/8/1
 * time:13:55
 * emaill:huangyh@thinkive.com
 * description:
 */

public class Test {
    public static void main(String[] args)
    {
        // 仓库对象,设置存货量
        Storage storage = new Storage();
        // 生产者对象
        Producer p1 = new Producer(storage);
        // 消费者对象
        Consumer c1 = new Consumer(storage);
        // 设置生产者产品生产数量
        p1.setNum(10);
        // 设置消费者产品消费数量
        c1.setNum(50);

        // 线程开始执行
        c1.start();
        p1.start();

    }
}
