package com.mook.java.proxy.staticproxy;

/**
 * @Author: maojunkai
 * @Date: 2018/7/4 下午11:56
 * @Description:
 */
public class StaticProxy implements StaticHello {
    private StaticHello staticHello;

    public StaticProxy(StaticHello staticHello) {
        this.staticHello = staticHello;
    }

    public void say() {
        System.out.println("before say hello");
        staticHello.say();
        System.out.println("after say hello");
    }
}
