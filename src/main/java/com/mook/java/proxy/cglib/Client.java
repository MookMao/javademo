package com.mook.java.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Author: maojunkai
 * @Date: 2018/7/9 下午8:24
 * @Description:
 * 值得注意的几点是：

1）使用CGLib代理的类不能是final修饰的，因为代理类需要继承主题类；

2）final修饰的方法不会被切入；

3）如果主题类的构造函数不是默认空参数的，那么在使用Enhancer类create的时候，选择create(java.lang.Class[] argumentTypes, java.lang.Object[] arguments) 方法。
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
