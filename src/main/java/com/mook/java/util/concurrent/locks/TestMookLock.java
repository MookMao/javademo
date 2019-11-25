package com.mook.java.util.concurrent.locks;

public class TestMookLock {
    private static MookLock lock = new MookLock();

    public static void main(String[] args) throws InterruptedException {
        new Thread1(lock).start();
        Thread.sleep(1000);
        new Thread2(lock).start();
    }

    static class Thread1 extends Thread {
        private MookLock lock;

        public Thread1(MookLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " lock");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unLock();
            System.out.println(Thread.currentThread().getName() + " unLock");
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
            System.out.println(Thread.currentThread().getName() + " lock");
            lock.unLock();
            System.out.println(Thread.currentThread().getName() + " unLock");
        }
    }
}
