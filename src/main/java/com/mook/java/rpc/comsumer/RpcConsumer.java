package com.mook.java.rpc.comsumer;

import com.mook.java.rpc.api.HelloService;
import com.mook.java.rpc.core.RpcFramework;

/**
 * @Author: maojunkai
 * @Date: 2018/11/4 下午10:58
 * @Description:
 */
public class RpcConsumer {
    public static void main(String[] args) throws Exception {
        HelloService service = RpcFramework.refer(HelloService.class, "127.0.0.1", 1234);
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {
            String hello = service.hello("World" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
