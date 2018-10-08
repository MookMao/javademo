package com.mook.java.cache.guava;

import com.google.common.cache.*;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Author: maojunkai
 * @Date: 2018/9/28 下午2:11
 * @Description: Google Guava Cache
 */
@Component
public class GuavaCacheUtil {

    private static final String INVALID_KEY = "invalidKey";

    private CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {
        @Override
        public Object load(Object s) throws Exception {
            Optional<String> result = Optional.ofNullable(null);
            if (INVALID_KEY.equals(s)) {
                // CacheLoader returned null将抛异常:InvalidCacheLoadException
                // TODO:可以使用Optional包装null值 或者 返回没有实际值的对象
                return result;
            }
            System.out.println(s + " is loaded from a cacheLoader!");
            result = Optional.ofNullable(s + "'s value");
            return result;
        }
    };

    private RemovalListener<Object, Object> removalListener = (removal) -> {
        System.out.println("[" + removal.getKey() + ":" + removal.getValue() + "] is evicted!");
    };

    /**
     * 基于Spring单例，所以可以不声明为静态变量
     */
    private static volatile LoadingCache<Object, Object> loadingCache;

    /**
     * Singleton线程安全的双重锁延迟初始化
     * synchronized 保证线程同步，也可以保证内存可见性
     * volatile 保证内存可见性，阻止指令重排(针对共享变量)
     * volatile和synchronized保证可见性的语义不一样
     * @return
     */
    public LoadingCache<Object, Object> getLoadingCache() {
        if (loadingCache == null) {
            synchronized (this) {
                if (loadingCache == null) {
                    loadingCache = CacheBuilder.newBuilder()
                            // 最大缓存条目数
                            .maximumSize(7L)
                            // 超时时间
                            .expireAfterWrite(10L, TimeUnit.MINUTES)
                            // 移除监听器
                            .removalListener(removalListener)
                            .build(cacheLoader);
                }
            }
        }
        return loadingCache;
    }
}
