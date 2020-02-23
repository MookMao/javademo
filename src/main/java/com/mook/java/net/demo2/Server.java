package com.mook.java.net.demo2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: maojunkai
 * @Date: 2018/11/27 上午12:38
 * @Description:
 */
public class Server {
    public static void main(String[] args) {
        try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            // bind & listen
            ServerSocket serverSocket = new ServerSocket(8886);
            // serverSocket.setSoTimeout(10);
            // 记录客户端的数量
            int count = 0;
            System.out.println("***服务器即将启动，等待客户端的连接***");
            // 循环监听等待客户端的连接
            while(true){
                Socket socket = null;
                // 调用accept()方法从请求队列backlog里获取客户端的连接，并创建服务端的Socket实例
                socket = serverSocket.accept();
                // 设置read阻塞时限
                socket.setSoTimeout(10);

                // 创建一个新的线程：一个连接对应一个线程来负责处理业务逻辑
                ServerThread serverThread = new ServerThread(socket);
                // 启动线程
                serverThread.start();

                // 统计客户端的数量
                count++;
                System.out.println("客户端的数量：" + count);
                // 客户端ip地址
                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的IP:端口号：" + address.getHostAddress() + ":" + socket.getPort());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
