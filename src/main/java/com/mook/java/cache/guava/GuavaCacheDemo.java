package com.mook.java.cache.guava;

import com.google.common.cache.*;

import java.util.Optional;
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
        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {
            @Override
            public Object load(Object s) throws Exception {
                Optional<String> result = Optional.ofNullable(null);
                if (invalidKey.equals(s)) {
                    // CacheLoader returned null将抛异常:InvalidCacheLoadException
                    // TODO:可以使用Optional包装null值 或者 返回没有实际值的对象
                    return result;
                }
                System.out.println(s + " is loaded from a cacheLoader!");
                result = Optional.ofNullable(s + "'s value");
                return result;
            }
        };

        RemovalListener<Object, Object> removalListener = new RemovalListener<Object, Object>() {
            @Override
            public void onRemoval(RemovalNotification<Object, Object> removal) {
                System.out.println("[" + removal.getKey() + ":" + removal.getValue() + "] is evicted!");
            }
        };

        LoadingCache<Object, Object> loadingCache = CacheBuilder.newBuilder()
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
            Optional<String> result = (Optional<String>)loadingCache.get("invalidKey");
            System.out.println(result.isPresent());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
