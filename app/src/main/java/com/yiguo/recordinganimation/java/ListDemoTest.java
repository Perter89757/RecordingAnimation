package com.yiguo.recordinganimation.java;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListDemoTest {
    public static void main(String[] a) {
        ExecutorService notifierThread = Executors.newSingleThreadExecutor();

        final ArrayList<String> myList = new ArrayList<>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");

//        notifierThread.submit(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (myList) {
//                    for (String item : myList) {
//                        System.out.println(Thread.currentThread().getName()+"遍历集合 value = " + item);
//                        try {
//                            Thread.sleep(200);
//                        } catch (Exception e) {
//                            System.out.println("list集合" + e.toString());
//                        }
//                    }
//                }
//            }
//        });
//        notifierThread.submit(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (myList) {
//                    Iterator<String> it = myList.iterator();
//                    while (it.hasNext()) {
//                        String value = it.next();
//                        if (value.equals("3")) {
//                            // myList.remove(value);// error ConcurrentModificationException
//                            it.remove();// error
//                        }
//                        System.out.println(Thread.currentThread().getName()+"遍历集合 value = " + value);
//                        try {
//                            Thread.sleep(200);
//                        } catch (Exception e) {
//                            System.out.println("list集合" + e.toString());
//                        }
//                    }
//                }
//            }
//        });

//        Iterator<String> it = myList.iterator();
//        while (it.hasNext()) {
//            String value = it.next();
//            if (value.equals("3")) {
//                // myList.remove(value);// error ConcurrentModificationException
//                it.remove();// error
//            }
//        }
//        System.out.println("list集合" + myList.toString());

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (myList) {
//                    for (String item : myList) {
//                        System.out.println(Thread.currentThread().getName()+"遍历集合 value = " + item);
//                        try {
//                            Thread.sleep(200);
//                        } catch (Exception e) {
//                            System.out.println("list集合" + e.toString());
//                        }
//                    }
//                }
//
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (myList) {
//                    Iterator<String> it = myList.iterator();
//                    while (it.hasNext()) {
//                        String value = it.next();
//                        if (value.equals("3")) {
//                            // myList.remove(value);// error ConcurrentModificationException
//                            it.remove();// error
//                        }
//                        System.out.println(Thread.currentThread().getName()+"遍历集合 value = " + value);
//                        try {
//                            Thread.sleep(200);
//                        } catch (Exception e) {
//                            System.out.println("list集合" + e.toString());
//                        }
//                    }
//                }
//            }
//        }).start();


         final TestSynchronized test = new TestSynchronized();
//
//        Thread thread1 = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//               // TestSynchronized.minus();
//                test.minus2();
//                test.minus3();
//
//            }
//        });
//
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                   test.minus2();
                //test.m1();
            }
        });
        Thread thread3 = new Thread(new Runnable() {

            @Override
            public void run() {
               test.minus3();
              //  test.m2();
            }
        });
//
//        thread1.start();
      thread2.start();
         thread3.start();
    }
}
