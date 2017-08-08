package com.yiguo.recordinganimation.Producers;

/**
 * author: huang_yanhui
 * data:2017/8/1
 * time:13:51
 * emaill:huangyh@thinkive.com
 * description:仓库类Storage实现缓冲区
 */

import java.util.concurrent.LinkedBlockingQueue;

public class Storage
{
    // 仓库最大存储量
    private final int MAX_SIZE = 100;

    // 仓库存储的载体
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(100);

    // 生产num个产品
    public void produce(int num)
    {
        // 生产条件满足情况下，生产num个产品
        for (int i = 1; i <= num; ++i) {
            try {
                // 放入产品，达到最大值 自动阻塞
                list.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("【生产" + list.size()+"个"+System.currentTimeMillis());
        }
    }

    // 消费num个产品
    public void consume(int num) {
        // 消费条件满足情况下，消费num个产品
        for (int i = 1; i <= num; ++i) {
            try {
                System.out.println("【消费前仓库还剩:"+list.size()+"个"+System.currentTimeMillis());
                list.take();
                // 消费产品，自动阻塞
                System.out.println("【消费后还剩:"+list.size()+"个"+System.currentTimeMillis());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // set/get方法
    public LinkedBlockingQueue<Object> getList() {
        return list;
    }

    public void setList(LinkedBlockingQueue<Object> list) {
        this.list = list;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }
}
