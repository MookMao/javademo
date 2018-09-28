package com.mook.java.jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @Author: maojunkai
 * @Date: 2018/9/6 下午11:20
 * @Description:
 */
public class ReferenceDemo {
    public static void main(String[] args) {
        testSoftReference();
        testPhantomReferenceRegisteredWithQueue();
    }

    private static void testSoftReference() {
        Object obj = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(obj);
        obj = null;
        System.out.println(softReference.get());
    }

    private static void testSoftReferenceRegisteredWithQueue() {

    }

    private static void testPhantomReferenceRegisteredWithQueue() {
        Object obj = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference<Object> phantomReference = new PhantomReference<Object>(obj, referenceQueue);
        obj = null;
        System.gc();
        Reference<Object> reference = null;
        if (phantomReference.isEnqueued()) {
            System.out.println("this reference object has been enqueued");
        }
    }
}
