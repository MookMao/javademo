package com.mook.java.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Author: maojunkai
 * @Date: 2018/7/7 下午2:51
 * @Description:
 */
public class DynamicTestMain {
    public static void main(String[] args){
        Subject target = new RealSubject();
        InvocationHandler handler = new DynamicInvocationHandler(target);
        // 创建代理实例(使用Proxy类和自定义的调用处理逻辑(handler)来生成一个代理对象)
        Subject proxyInstance = (Subject) Proxy.
                newProxyInstance(Subject.class.getClassLoader(), new Class<?>[]{Subject.class}, handler);
        // 调用方法时，转移给handler接管，由其中的invoke()方法实际完成方法执行
        proxyInstance.hello("动态代理");

        System.out.println("代理对象：" + proxyInstance.getClass().getName());
        System.out.println("代理对象父类：" + proxyInstance.getClass().getSuperclass().getName());
        System.out.println("代理对象实现的接口：");
        Class[] interfaces = proxyInstance.getClass().getInterfaces();
        Arrays.stream(interfaces).forEach(o -> {
            System.out.println(o.getName());
        });
    }
}
