package com.mook.java.jvm;

import java.lang.ref.*;

/**
 * @Author: maojunkai
 * @Date: 2018/9/6 下午11:20
 * @Description:
 */
public class ReferenceDemo {
    public static void main(String[] args) {
//        testSoftReference();
//        testSoftReferenceRegisteredWithQueue();
//        testWeakReferenceRegisteredWithQueue();
        testPhantomReferenceRegisteredWithQueue();
//        soft();
//        weak();
    }

    /**
     * -Xms10M -Xmx10M -Xmn5M -XX:+PrintGCDetails
     */
    private static void soft() {
        SoftReference[] softArr = new SoftReference[4];
        softArr[0] = new SoftReference<byte[]>(new byte[1024*1024*2]);
        System.out.println("GC 前===>"+softArr[0].get());
        System.gc();
        System.out.println("第一次GC后：===>"+softArr[0].get());
        softArr[1] = new SoftReference<byte[]>(new byte[1024*1024*2]);
        System.gc();
        System.out.println("第二次GC后===>"+softArr[0].get());
        softArr[2] = new SoftReference<byte[]>(new byte[1024*1024*2]);
        System.gc();
        System.out.println("第三次GC后===>"+softArr[0].get());
        softArr[3] = new SoftReference<byte[]>(new byte[1024*1024*2]);
        //System.gc();  这里都不需要显示执行，因为堆内存已经满了，虚拟机自己会执行。
        // null
        System.out.println("第四次GC后===>"+softArr[0].get());
        // null
        System.out.println("第四次GC后===>"+softArr[1].get());
        // null
        System.out.println("第四次GC后===>"+softArr[2].get());
        // null
        System.out.println("第四次GC后===>"+softArr[3].get());
    }

    /**
     * -Xms10M -Xmx10M -Xmn5M -XX:+PrintGCDetails
     */
    private static void weak(){
        WeakReference<Integer> weak = new WeakReference<Integer>(new Integer(100));
        System.out.println("GC 前===>"+weak.get());
        System.gc();
        System.out.println("GC 后===>"+weak.get());
    }

    private static void testSoftReference() {
        Object obj = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(obj);
        obj = null;
        System.out.println(softReference.get());
    }

    private static void testSoftReferenceRegisteredWithQueue() {
        Object obj = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        SoftReference<Object> softReference = new SoftReference<Object>(obj, referenceQueue);
        obj = null;
        System.gc();
        try {
            // 返回null，因为软引用所引用的对象没有被回收
            System.out.println(referenceQueue.remove(1000L));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void testWeakReferenceRegisteredWithQueue() {
        Object obj = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        WeakReference<Object> weakReference = new WeakReference<>(obj, referenceQueue);
        obj = null;
        System.gc();
        try {
            System.out.println(referenceQueue.remove(1000L));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testPhantomReferenceRegisteredWithQueue() {
        Object obj = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference<Object> phantomReference = new PhantomReference<Object>(obj, referenceQueue);
        obj = null;
        System.gc();
        Reference<Object> reference = null;
        try {
            reference = referenceQueue.remove(1000L);
            if (reference != null) {
                System.out.println("referenceQueue: " + reference);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (phantomReference.isEnqueued()) {
            System.out.println("this reference object has been enqueued");
        }
    }
}
