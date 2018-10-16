package com.mook.java.jvm.oom;

/**
 * @Author: maojunkai
 * @Date: 2018/10/15 下午11:48
 * @Description: Java栈出现 StackOverflowError
 * -Xss160k
 */
public class JavaVMStackSOF {
    private Long stackLength = 1L;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + javaVMStackSOF.stackLength);
            throw e;
        }
    }

}
