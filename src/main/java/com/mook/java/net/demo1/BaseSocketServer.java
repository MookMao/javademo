package com.mook.java.net.demo1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: maojunkai
 * @Date: 2018/11/23 上午10:45
 * @Description: Socket连接
 * 管理客户连接请求的任务是由操作系统来完成的. 操作系统把这些连接请求存储在一个先进先出的队列中. 许多操作系统限定了队列的最大长度, 一般为 50 .
 * 当队列中的连接请求达到了队列的最大容量时, 服务器进程所在的主机会拒绝新的连接请求.
 * 只有当服务器进程通过 ServerSocket 的 accept() 方法从队列中取出连接请求, 使队列腾出空位时, 队列才能继续加入新的连接请求.
 */
public class BaseSocketServer {

    /**
     * 侦听端口号
     */
    private int port = 1122;

    /**
     * 连接请求队列长度
     */
    private int backlog = 3;

    /**
     * server socket
     */
    private ServerSocket serverSocket;

    public BaseSocketServer() throws IOException {
        serverSocket = new ServerSocket(port, backlog);
        // java.net.BindException: Address already in use (Bind failed)
        // ServerSocket serverSocket1 = new ServerSocket(port, backlog);
        System.out.println("服务端启动：监听来自客户端的请求");
    }

    public void service() {
        while (true) {
            Socket socket = null;
            try {
                // 从队列中获取连接，并创建Socket实例
                socket = serverSocket.accept();
                System.out.println("New connection accepted（remote address:port）：" +
                        socket.getInetAddress() + ":" + socket.getPort());
                System.out.println("The local port number to which this socket is connected：" + socket.getLocalPort());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BaseSocketServer server = new BaseSocketServer();
        // 测试连接队列
         Thread.sleep(60000 * 10);
        server.service();
    }
}
