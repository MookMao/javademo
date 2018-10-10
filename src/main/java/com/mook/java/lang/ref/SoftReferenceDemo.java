package com.mook.java.lang.ref;

import java.lang.ref.SoftReference;

/**
 * @Author: maojunkai
 * @Date: 2018/10/10 下午2:47
 * @Description: 软引用，当内部不足（会出现OOM）时，回收该引用指向的对象
 * SoftReference： SoftReference 在“弱引用”中属于最强的引用。
 * SoftReference所指向的对象，当没有强引用指向它时，会在内存中停留一段的时间，垃圾回收器会根据 JVM 内存的使用情况（内存的紧缺程度）以及 SoftReference
 * 的 get() 方法的调用情况来决定是否对其进行回收。 具体使用一般是通过 SoftReference
 * 的构造方法，将需要用弱引用来指向的对象包装起来。当需要使用的时候，调用 SoftReference 的 get() 方法来获取。当对象未被回收时
 * SoftReference 的 get() 方法会返回该对象的强引用。
 *
 * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 */
public class SoftReferenceDemo {

    private static final int _1MB = 1024 * 1024;

    /**
     * 内存不紧缺，不会回收软引用关联的对象
     */
    public static void test1(){
        byte[] bytes = new byte[4 * _1MB];
        SoftReference<byte[]> softReference = new SoftReference<byte[]>(bytes);
        System.out.println("GC前：" + softReference.get());

        bytes = null;

        System.gc();
        // 软引用所指向的对象会根据内存使用情况来决定是否回收，这里内存还充足，所以不会被回收。
        System.out.println("GC后：" + softReference.get());

    }

    /**
     * 内存不足，回收软引用关联的对象
     */
    private static void test2() {
        SoftReference<byte[]> sr0 = new SoftReference<byte[]>(new byte[4 * _1MB]);
        SoftReference<byte[]> sr1 = new SoftReference<byte[]>(new byte[4 * _1MB]);
        SoftReference<byte[]> sr2 = new SoftReference<byte[]>(new byte[4 * _1MB]);
        SoftReference<byte[]> sr3 = new SoftReference<byte[]>(new byte[4 * _1MB]);
        SoftReference<byte[]> sr4 = new SoftReference<byte[]>(new byte[4 * _1MB]);
        SoftReference<byte[]> sr5 = new SoftReference<byte[]>(new byte[4 * _1MB]);

        System.out.println(sr0.get());
        System.out.println(sr1.get());
        System.out.println(sr2.get());
        System.out.println(sr3.get());
        System.out.println(sr4.get());
        System.out.println(sr5.get());
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }
}
