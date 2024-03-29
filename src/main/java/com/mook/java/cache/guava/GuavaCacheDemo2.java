package com.mook.java.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @Author: maojunkai
 * @Date: 2018/10/8 上午11:23
 * @Description: 基于Callable的自动加载方式
 */
public class GuavaCacheDemo2 {
    static Cache<String, String> testCache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .build();

    public static void main(String[] args){
        testCache.put("1234","45");

        System.out.println(testCache.getIfPresent("key6"));

        try {

            System.out.println(testCache.get("123", new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "134";
                }
            }));

            System.out.println(testCache.get("1234", () -> {return "134";}));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
