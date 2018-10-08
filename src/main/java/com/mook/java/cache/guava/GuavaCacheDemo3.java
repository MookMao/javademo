package com.mook.java.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @Author: maojunkai
 * @Date: 2018/10/8 下午2:13
 * @Description: 基于引用的回收，统计信息
 */
public class GuavaCacheDemo3 {
    static Cache<String, Object> testCache = CacheBuilder.newBuilder()
            .weakValues()
            .recordStats()
            .build();

    public static void main(String[] args){
        Object obj1 = new Object();

        testCache.put("1234",obj1);

        System.out.println(testCache.getIfPresent("1234"));

        obj1 = new String("123");

        System.gc();

        System.out.println(testCache.getIfPresent("1234"));

        System.out.println(testCache.stats());

    }
}
