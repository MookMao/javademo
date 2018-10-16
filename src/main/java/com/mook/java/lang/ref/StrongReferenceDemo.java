package com.mook.java.lang.ref;

/**
 * @Author: maojunkai
 * @Date: 2018/10/10 下午1:09
 * @Description: 强引用，垃圾收集器是不会去理会的
 */
public class StrongReferenceDemo {

    private static final int _1MB = 1024 * 1024;

    /**
     * 我们都知道 JVM 中对象是被分配在堆（heap）上的，当程序行动中不再有引用指向这个对象时，这个对象就可以被垃圾回收器所回收。
     * 这里所说的引用也就是我们一般意义上申明的对象类型的变量（如 String, Object, ArrayList 等），区别于原始数据类型的变量（如
     * int, short, long 等）也称为强引用。
     *
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 此处的 tag 引用就称之为强引用。
         * 而强引用有以下特征：
         * 	强引用可以直接访问目标对象。
         * 	强引用所指向的对象在任何时候都不会被系统回收。
         * 	强引用可能导致内存泄漏。
         * 我们要讨论的这三种 Reference较之于强引用而言都属于“弱引用”，也就是他们所引用的对象只要没有强引用，就会根据条件被 JVM
         * 的垃圾回收器所回收，它们被回收的时机以及用法各不相同。下面分别来进行讨论。
         */
        byte[] bytes = new byte[7 * _1MB];

//        bytes = null;

        System.gc();
    }
}
