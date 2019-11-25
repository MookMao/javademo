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
            if (state == 0 && unsafe.compareAndSwapInt(this, valueOffset, 0, 1)) {
                break;
            }
            queue.offer(Thread.currentThread());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName());
        }
    }

    public void unLock() {
        if (state != 0) {
            if (queue.size() > 0) {
                queue.stream().forEach(LockSupport::unpark);
            }
            state = 0;
        }
    }
}
