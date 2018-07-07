package com.mook.java.proxy.dynamicproxy;

/**
 * @Author: maojunkai
 * @Date: 2018/7/5 上午12:35
 * @Description:
 */
public class RealSubject implements Subject {
    public void hello(String str) {
        System.out.println("hello: " + str);
    }
}
