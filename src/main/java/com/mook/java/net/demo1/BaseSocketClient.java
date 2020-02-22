package com.mook.java.net.demo1;

import java.net.Socket;

/**
 * @Author: maojunkai
 * @Date: 2018/11/26 下午10:50
 * @Description:
 * 对于客户进程, 如果它发出的连接请求被加入到服务器的请求连接队列中, 就意味着客户与服务器的连接建立成功, 客户进程从 Socket 构造方法中正常返回.
 * 如果客户进程发出的连接请求被服务器拒绝, Socket 构造方法就会抛出 ConnectionException.
 */
public class BaseSocketClient {
    public static void main(String[] args) throws Exception{
        // 客户端请求连接数
        final int length = 10;
        // 服务器名
        String host = "localhost";
        // 服务端的侦听端口
        int port = 1122;
        Socket[] socket = new Socket[length];
        for(int i = 0; i < length; i++){
            socket[i] = new Socket(host, port);
            System.out.println("第" + (i + 1) + "次连接成功！" + "remote address:port：" + socket[i].getInetAddress() + ":" + socket[i].getPort());
            System.out.println("The local port number to which this socket is connected：" + socket[i].getLocalPort());
        }
        Thread.sleep(3000);
        for(int i = 0; i < length; i++){
            socket[i].close();
        }
    }
}
