package com.mook.java.proxy.cglib;

/**
 * @Author: maojunkai
 * @Date: 2018/7/9 下午7:38
 * @Description: 被代理对象
 */
public class HelloServiceImpl {
    public void sayHello() {
        System.out.println("hello cglib");
    }
}
