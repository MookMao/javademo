package com.mook.java.util.concurrent.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * Unsafe测试类
 */
public class UnsafeTest {
    private static final Thread CURRENT_THREAD = Thread.currentThread();

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        unsafePutGetInt(unsafe);
        unsafeAllocateMemory(unsafe);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // LockSupport.unpark(CURRENT_THREAD);
                CURRENT_THREAD.interrupt();
            }
        }).start();
        unsafePark(unsafe);

        unsafeCAS(unsafe);
    }

    /**
     * Unsafe修改内存:Unsafe 获取对象字段偏移量，及修改偏移量对应字段的值
     * @param unsafe
     * @throws Exception
     */
    public static void unsafePutGetInt(Unsafe unsafe) throws Exception {
        class Student {
            private int age = 5;

            public int getAge() {
                return age;
            }
        }

        Student student = new Student();
        System.out.println(student.getAge());

        Field field = student.getClass().getDeclaredField("age");
        unsafe.putInt(student, unsafe.objectFieldOffset(field), 10);

        System.out.println(student.getAge());
    }

    /**
     * 在非Java堆中分配内存
     * @param unsafe
     * @throws Exception
     */
    public static void unsafeAllocateMemory(Unsafe unsafe) throws Exception {
        int BYTE = 1;

        long address = unsafe.allocateMemory(BYTE);
        unsafe.putByte(address, (byte) 10);
        byte num = unsafe.getByte(address);

        System.out.println(num);
        // 释放内存
        unsafe.freeMemory(address);
    }

    /**
     * CAS操作
     * @param unsafe
     * @throws NoSuchFieldException
     */
    public static void unsafeCAS(Unsafe unsafe) throws NoSuchFieldException {
        Integer a = 1;
        long valueOffset = unsafe.objectFieldOffset
                (Integer.class.getDeclaredField("value"));
        unsafe.compareAndSwapInt(a, valueOffset, 2, 3);
        System.out.println(a);
        unsafe.compareAndSwapInt(a, valueOffset, 1, 3);
        System.out.println(a);
    }

    /**
     * 阻塞当前线程：park用于挂起当前线程，如果许可可用，会立马返回，并消费掉许可
     * park(Object): 恢复的条件为 1：线程调用了unpark; 2:其它线程中断了线程；3：发生了不可预料的事情
     * @param unsafe
     */
    public static void unsafePark(Unsafe unsafe) {
        // unsafe.park(false, 0L);
        LockSupport.park();
    }
}
