package com.mook.java.util.concurrent.locks;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现简单的互斥锁
 */
public class MookLock {
    /**
     * 锁状态：
     * 0  未锁定
     * 1  已锁定
     */
    private volatile int state = 0;

    private static final Unsafe unsafe;

    private static final long valueOffset;

    private Thread holdLock;

    private ConcurrentLinkedQueue<Thread> queue = new ConcurrentLinkedQueue<>();

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            valueOffset = unsafe.objectFieldOffset
                    (MookLock.class.getDeclaredField("state"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    public void lock() {
        for (; ; ) {
            if (state == 1 && holdLock == Thread.currentThread()) {
                System.out.println(Thread.currentThread().getName() + " get lock");
                break;
            }
            if (state == 0 && unsafe.compareAndSwapInt(this, valueOffset, 0, 1)) {
                holdLock = Thread.currentThread();
                System.out.println(Thread.currentThread().getName() + " get lock");
                break;
            }
            queue.offer(Thread.currentThread());
            LockSupport.park();
        }
    }

    public void unLock() {
        if (holdLock == Thread.currentThread() && unsafe.compareAndSwapInt(this, valueOffset, 1, 0)) {
            System.out.println(Thread.currentThread().getName() + " unLock");
            for (int i = 0; i < queue.size(); i++) {
                LockSupport.unpark(queue.poll());
            }
        }
    }
}
