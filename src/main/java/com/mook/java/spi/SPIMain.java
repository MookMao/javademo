package com.mook.java.spi;

import com.mook.java.spi.api.HelloInterface;

import java.util.ServiceLoader;


/**
 * @Author: maojunkai
 * @Date: 2018/7/15 下午6:00
 * @Description:
 */
public class SPIMain {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getClass().getResource("/").getPath());
        System.out.println("classPath:"+System.getProperty("java.class.path"));

        ServiceLoader<HelloInterface> loaders = ServiceLoader.load(HelloInterface.class);

        for (HelloInterface in : loaders) {
            in.sayHello();
        }
    }
}
