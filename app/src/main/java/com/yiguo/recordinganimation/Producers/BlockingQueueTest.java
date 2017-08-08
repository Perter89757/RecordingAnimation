package com.yiguo.recordinganimation.Producers;

/**
 * author: huang_yanhui
 * data:2017/8/1
 * time:15:16
 * emaill:huangyh@thinkive.com
 * description:
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class BlockingQueueTest{
    public static void main(String[] args)throws Exception{
        //创建一个容量为1的BlockingQueue
        BlockingQueue<String> b=new ArrayBlockingQueue<>(3);
        //启动3个生产者线程
        new Producer1(b).start();
        new Producer1(b).start();
        new Producer1(b).start();
        //启动一个消费者线程
        new Consumer1(b).start();

    }
}
class Producer1 extends Thread{
    private BlockingQueue<String> b;

    public Producer1(BlockingQueue<String> b){
        this.b=b;

    }
    public synchronized void run(){
        String [] str=new String[]{
                "java",
                "struts",
                "Spring"
        };

        for(int i=0;i<4;i++){
            System.out.println(getName()+"生产者准备生产集合元素！");
            try{

                b.put(str[i%3]);
                sleep(1000);
                //尝试放入元素，如果队列已满，则线程被阻塞

            }catch(Exception e){System.out.println(e);}
            System.out.println(getName()+"生产完成："+b);
        }

    }
}
class Consumer1 extends Thread{
    private BlockingQueue<String> b;
    public Consumer1(BlockingQueue<String> b){
        this.b=b;
    }
    public  synchronized  void run(){

        while(true){
            System.out.println(getName()+"消费者准备消费集合元素！");
            try{
                sleep(1000);
                //尝试取出元素，如果队列已空，则线程被阻塞
                String take = b.take();
            }catch(Exception e){System.out.println(e);}
            System.out.println(getName()+"消费完："+b);
        }

    }
}
