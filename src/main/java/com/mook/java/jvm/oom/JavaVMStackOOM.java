package com.mook.java.jvm.oom;

/**
 * @Author: maojunkai
 * @Date: 2018/10/15 下午11:58
 * @Description: 创建线程导致内存溢出异常
 * 分配一个大内存给栈空间：-Xss2M
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {}
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM javaVMStackOOM = new JavaVMStackOOM();
        javaVMStackOOM.stackLeakByThread();
    }
}
