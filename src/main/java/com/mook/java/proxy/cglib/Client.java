package com.mook.java.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Author: maojunkai
 * @Date: 2018/7/9 下午8:24
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/mook/IdeaProjects/JavaDemo");
        // 利用Enhancer类生成代理类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloServiceImpl.class);
        enhancer.setCallback(new HelloMethodInterceptor());
        HelloServiceImpl helloService = (HelloServiceImpl) enhancer.create();
        helloService.sayHello();
    }
}
