package com.mook.java.util.concurrent.locks;

public class TestMookLock {
    private static MookLock lock = new MookLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread1(lock).start();
        Thread.sleep(1000);
        for (int i = 0; i < 100; i++) {
            new Thread2(lock).start();
        }
    }

    static class Thread1 extends Thread {
        private MookLock lock;

        public Thread1(MookLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
        }
    }

    static class Thread2 extends Thread {
        private MookLock lock;

        public Thread2(MookLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
        }
    }
}
