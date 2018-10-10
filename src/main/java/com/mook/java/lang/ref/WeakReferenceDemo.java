package com.mook.java.lang.ref;

import java.lang.ref.WeakReference;

/**
 * @Author: maojunkai
 * @Date: 2018/10/10 下午3:02
 * @Description: 弱引用所指向的对象只要进行 GC，就会自动进行回收，get() 返回 null。
 *
 * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 */
public class WeakReferenceDemo {

    private static final int _1MB = 1024 * 1024;

    public static void test1() {
        WeakReference<byte[]> wr = new WeakReference<byte[]>(new byte[4 * _1MB]);
        System.out.println("GC前：" + wr.get());

        System.gc();
        System.out.println("GC后：" + wr.get());
    }

    public static void main(String[] args) {
        test1();
    }
}
