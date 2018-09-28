package com.mook.java.cache.guava;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

/**
 * @Author: maojunkai
 * @Date: 2018/9/28 下午2:11
 * @Description: Google Guava Cache
 */
public class GuavaCacheDemo {
    public static void main(String[] args) {
        int initialCacheSize = 10;
        String invalidKey = "invalidKey";
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                if (invalidKey.equals(s)) {
                    // CacheLoader returned null将抛异常:InvalidCacheLoadException
                    // TODO:可以使用Optional包装null值 或者 返回没有实际值的对象
                    return null;
                }
                System.out.println(s + " is loaded from a cacheLoader!");
                return s + "'s value";
            }
        };

        RemovalListener<String, String> removalListener = new RemovalListener<String, String>() {
            @Override
            public void onRemoval(RemovalNotification<String, String> removal) {
                System.out.println("[" + removal.getKey() + ":" + removal.getValue() + "] is evicted!");
            }
        };

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                // 最大缓存条目数
                .maximumSize(7)
                // 超时时间
                .expireAfterWrite(10L, TimeUnit.MINUTES)
                // 移除监听器
                .removalListener(removalListener)
                .build(cacheLoader);

        for (int i = 0; i < initialCacheSize; i++) {
            String key = "key" + i;
            String value = "value" + i;
            loadingCache.put(key, value);
            System.out.println("[" + key + ":" + value + "] is put into cache!");
        }

        System.out.println(loadingCache.getIfPresent("key6"));

        try {
            System.out.println(loadingCache.get("validKey"));
            System.out.println(loadingCache.get("invalidKey"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
