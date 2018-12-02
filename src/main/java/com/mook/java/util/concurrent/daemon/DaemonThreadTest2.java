package com.mook.java.util.concurrent.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @Author: maojunkai
 * @Date: 2018/12/2 下午8:48
 * @Description: 只有所有的用户线程结束时，守护线程才会退出
 */
public class DaemonThreadTest2 {

    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread childThread = new Thread(new ClildThread());
                childThread.setDaemon(true);
                childThread.start();
                System.out.println("I'm main thread...");
            }
        });
        mainThread.start();

        Thread otherThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("I'm other user thread...");
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        otherThread.start();
    }
}

