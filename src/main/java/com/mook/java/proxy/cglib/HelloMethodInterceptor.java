package com.mook.java.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: maojunkai
 * @Date: 2018/7/9 下午7:39
 * @Description: 实现MethodInterceptor接口，定义方法的拦截器
 */
public class HelloMethodInterceptor implements MethodInterceptor {
    /**
     *
     * @param o 代理对象
     * @param method 委托类方法
     * @param objects 方法参数
     * @param methodProxy 代理方法的MethodProxy对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method: " + method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("after method: " + method.getName());
        return object;
    }
}
