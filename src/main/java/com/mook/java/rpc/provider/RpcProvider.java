package com.mook.java.rpc.provider;

import com.mook.java.rpc.api.HelloService;
import com.mook.java.rpc.biz.HelloServiceImpl;
import com.mook.java.rpc.core.RpcFramework;

/**
 * @Author: maojunkai
 * @Date: 2018/11/4 下午10:57
 * @Description:
 */
public class RpcProvider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        RpcFramework.export(service, 1234);
    }
}
