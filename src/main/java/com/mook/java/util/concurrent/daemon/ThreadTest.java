package com.mook.java.util.concurrent.daemon;

/**
 * @Author: maojunkai
 * @Date: 2018/12/2 下午8:59
 * @Description: 主线程结束时，将子线程结束掉
 */
import java.util.concurrent.TimeUnit;

public class ThreadTest {

    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("主线程开始...");
                Thread sonThread = new Thread(new Thread1(Thread.currentThread()));
                sonThread.setDaemon(false);
                sonThread.start();

                try {
                    TimeUnit.MILLISECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("主线程结束");
            }
        });
        mainThread.start();
    }

}

class Thread1 implements Runnable {
    private Thread mainThread;

    public Thread1(Thread mainThread) {
        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        while (mainThread.isAlive()) {
            System.out.println("子线程运行中....");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
