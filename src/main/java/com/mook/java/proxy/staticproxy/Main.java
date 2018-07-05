package com.mook.java.proxy.staticproxy;

/**
 * @Author: maojunkai
 * @Date: 2018/7/5 上午12:03
 * @Description: 静态代理
 */
public class Main {
    public static void main(String[] args) {
        StaticHello staticHello = new StaticHelloImpl();
        StaticProxy staticProxy = new StaticProxy(staticHello);
        staticProxy.say();
    }
}
