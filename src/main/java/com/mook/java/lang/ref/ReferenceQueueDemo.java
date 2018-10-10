package com.mook.java.lang.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: maojunkai
 * @Date: 2018/10/10 下午3:21
 * @Description: 引用队列配合Reference的子类等使用，当引用对象所指向的内存空间被GC回收后，该引用对象则被追加到引用队列的末尾
 */
public class ReferenceQueueDemo {
    /**
     * 此类提供三个方法，没有实现queue接口，也没有实现其它接口类，只提供移除队列的三个方法如下：
     *
     * Reference<? extends T> ReferenceQueue#poll() ，从队列中出队一个元素，若队列为空则返回null。
     *
     * Reference<? extends T> ReferenceQueue#remove()
     * ，从队列中出队一个元素，若没有则阻塞直到有元素可出队。
     *
     * Reference<? extends T> ReferenceQueue#remove(long timeout)
     * ，从队列中出队一个元素，若没有则阻塞直到有元素可出队或超过timeout指定的毫秒数（由于采用wait(long
     * timeout)方式实现等待，因此时间不能保证）。
     *
     * @param args
     */
    public static void main(String[] args) {
        ReferenceQueue<Object> weakReferenceReferenceQueue = new ReferenceQueue<>();

        int byteSize = 1024;
        int mapSize = 10000;
        Object o = new Object();
        Map<WeakReference<byte[]>, Object> map = new HashMap<>(mapSize);

        //创建一个线程监听回收的对象
        new Thread(() -> {
                try {
                    int cnt = 0;
                    WeakReference<byte[]> k;
                    while ((k = (WeakReference) weakReferenceReferenceQueue.remove()) != null) {
                        // 在这里我们移除被回收对象的引用
                        map.remove(k);
                        System.out.println((cnt++) + "recycle:" + k);
                        System.out.println("map.size:" + map.size());
                    }


                } catch (InterruptedException e) {
                    //结束循环

                }
            }).start();

        for (int i = 0; i < mapSize; i++) {
            byte[] bytes = new byte[byteSize];
            map.put(new WeakReference<>(bytes, weakReferenceReferenceQueue), o);
        }
        // 发生GC时，只有弱引用指向的对象将被垃圾收集器回收
        System.gc();
        try {
            // 主线程阻塞会儿
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc over, map.size:" + map.size());
    }
}
