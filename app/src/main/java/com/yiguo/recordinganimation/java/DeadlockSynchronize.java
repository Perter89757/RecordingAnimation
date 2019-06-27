package com.yiguo.recordinganimation.java;

/**
 * 死锁测试
 * 互相持有对方的锁
 */
public class DeadlockSynchronize {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1){
                    try{
                        System.out.println(Thread.currentThread().getName()+"获得了lock1锁");
                        Thread.sleep(500);//sleep的原因是等待thread2获取锁
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                    synchronized (lock2){
                        System.out.println(Thread.currentThread().getName()+"获得了lock2锁");
                    }
                }
            }
        },"Thread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2){
                    try{
                        System.out.println(Thread.currentThread().getName()+"获得了lock2锁");
                        Thread.sleep(500);
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }
                    synchronized (lock1){
                        System.out.println(Thread.currentThread().getName()+"获得了lock1锁");
                    }
                }
            }
        },"Thread2");
        thread1.start();
        thread2.start();
    }

}
