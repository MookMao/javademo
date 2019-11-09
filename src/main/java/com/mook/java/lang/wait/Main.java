package com.mook.java.lang.wait;

/**
 * @Author: maojunkai
 * @Date: 2018/6/23 下午1:24
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Object lock = new Object();
        Object waitObject = new Object();
        MyThread1 myThread1 = new MyThread1(lock, waitObject);
        MyThread2 myThread2 = new MyThread2(lock, waitObject);
        myThread1.start();
        myThread2.start();
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
