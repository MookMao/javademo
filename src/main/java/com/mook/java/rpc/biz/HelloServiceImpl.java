package com.mook.java.rpc.biz;

import com.mook.java.rpc.api.HelloService;

/**
 * @Author: maojunkai
 * @Date: 2018/11/4 下午10:56
 * @Description:
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
