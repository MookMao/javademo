package com.mook.java.util.concurrent.daemon;

/**
 * @Author: maojunkai
 * @Date: 2018/12/2 下午8:43
 * @Description: 守护线程：用户线程结束时，守护线程也退出
 */

import java.util.concurrent.TimeUnit;

public class DaemonThreadTest {
    public static void main(String[] args) {
        // 用户线程
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread childThread = new Thread(new ClildThread());
                // 守护线程
                // childThread.setDaemon(true);
                childThread.start();
                System.out.println("I'm main thread...");
            }
        });
        mainThread.start();
    }
}

class ClildThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("I'm child thread..");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
