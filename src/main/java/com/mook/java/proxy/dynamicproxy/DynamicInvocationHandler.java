package com.mook.java.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: maojunkai
 * @Date: 2018/7/7 下午2:49
 * @Description:
 */
public class DynamicInvocationHandler implements InvocationHandler {
    /**
     * 对真实对象的引用
     */
    private Object target;

    public DynamicInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 目标方法之前执行
        System.out.println("Before DynamicProxy");
        System.out.println("Method:" + method.getName() + "(" + args.toString() + ")");
        // 通过反射机制来调用目标类方法
        method.invoke(target, args);
        // 目标方法之后执行
        System.out.println("After DynamicProxy");
        return null;
    }
}
