package com.yiguo.recordinganimation;

import com.yiguo.recordinganimation.callback.CallBack;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * author: huang_yanhui
 * data:2017/6/23
 * time:17:48
 * emaill:huangyh@thinkive.com
 * description:
 */

class ConsumerProducer {
    private final int LIMIT = 10;
    private BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>(LIMIT);

    /**
     * 写入数据
     *
     * @throws InterruptedException
     */
    public void produce(int b, CallBack callBack) {
        int value = 0;
        while (true) {
            try {
                blockingQueue.put(1);
                callBack.onsuceess("");
            } catch (InterruptedException e) {
                e.printStackTrace();
                callBack.onerror(e.getMessage());
            }
            System.out.println("写入数据");
        }
    }

    /**
     * 读取数据
     *
     * @throws InterruptedException
     */
    public int consume(CallBack callBack) throws InterruptedException {
        while (true) {
            int value = blockingQueue.take();
            if (value == 1) {
                return value;
            }
        }
    }

}

