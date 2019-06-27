package com.yiguo.recordinganimation.java;

/ 
public   class TestSynchronized {

    //private final Object object;
    private static final TestSynchronized sInstance = new TestSynchronized();

    public TestSynchronized() {
       // object = new Object();
    }

    public static synchronized void minus() {
        int count = 5;
        for (int i = 0; i < 5; i++) {
            count--;
            System.out.println(Thread.currentThread().getName() + " - " + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void minus2() {
        int count = 5;
        for (int i = 0; i < 5; i++) {
            count--;
            System.out.println(Thread.currentThread().getName() + " - " + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void minus3() {
     //   synchronized (sInstance) {
            int count = 5;
            for (int i = 0; i < 5; i++) {
                count--;
                System.out.println(Thread.currentThread().getName() + " - " + count);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
      //  }

    }

    int b = 0;

    public synchronized void m1()  {
        b = 1000;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("b= "+b);
    }

    public void m2(){
        System.out.println(b);
    }


}
