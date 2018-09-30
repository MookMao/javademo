package com.mook.java.cache.guava;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @Author: maojunkai
 * @Date: 2018/9/30 上午11:04
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class GuavaCacheUtilTest {

    @Resource
    private GuavaCacheUtil guavaCacheUtil;

    @Test
    public void getLoadingCache() {
        int initialCacheSize = 10;
        for (int i = 0; i < initialCacheSize; i++) {
            String key = "key" + i;
            String value = "value" + i;
            guavaCacheUtil.getLoadingCache().put(key, value);
            System.out.println("[" + key + ":" + value + "] is put into cache!");
        }

        System.out.println(guavaCacheUtil.getLoadingCache().getIfPresent("key6"));

        try {
            Optional<String> result = (Optional<String>) guavaCacheUtil.getLoadingCache().get("validKey");
            System.out.println(result.get());
            result = (Optional<String>)guavaCacheUtil.getLoadingCache().get("invalidKey");
            System.out.println(result.isPresent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
