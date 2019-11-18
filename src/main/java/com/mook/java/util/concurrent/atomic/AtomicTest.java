package com.mook.java.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * AtomicInteger通过Unsafe提供的CAS保证原子操作
 * LongAdder提供计数/减数功能，保证原子性操作，在高并发下性能比AtomicInteger好（AtomicInteger高并发下自旋多，CPU高）
 */
public class AtomicTest {
    private static AtomicInteger count = new AtomicInteger();

    private volatile static int sum = 0;

    private volatile static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                sum++;
                count.getAndIncrement();
                longAdder.increment();
            }
            System.out.println(Thread.currentThread().getName() + ":" + sum);
            System.out.println(Thread.currentThread().getName() + ":" + count.get());
            System.out.println(Thread.currentThread().getName() + ":" + longAdder.sum());
        };
        new Thread(task).start();
        new Thread(task).start();
    }
}
