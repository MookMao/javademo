package com.mook.java.net.demo3;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @Author: maojunkai
 * @Date: 2018/11/28 上午10:46
 * @Description:
 */
public class ServerThreadPool {
    /**
     * 服务端监听端口
     */
    private int port = 8886;

    /**
     * 服务端监听Socket
     */
    private ServerSocket serverSocket;

    /**
     * 线程池
     */
    private ExecutorService executorService;

    /**
     * 单CPU中线程数
     */
    private static int POOL_SIZE = 4;

    public ServerThreadPool() throws IOException {
        //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
        // bind & listen
        serverSocket = new ServerSocket(port);

        // executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);

        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * POOL_SIZE,
                                                 Runtime.getRuntime().availableProcessors() * POOL_SIZE,
                                                 60L,
                                                 TimeUnit.SECONDS,
                                                 new LinkedBlockingQueue<Runnable>(),
                                                 new NamedThreadFactory(),
                                                 new ThreadPoolExecutor.AbortPolicy());

        System.out.println("***服务器即将启动，等待客户端的连接***");
    }

    public void service() {
        // 记录客户端的数量
        int count = 0;
        // 循环监听等待客户端的连接
        while(true){
            Socket socket = null;
            // 调用accept()方法从请求队列backlog里获取客户端的连接，并创建服务端的Socket实例
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 使用线程池来处理请求业务逻辑
            executorService.execute(new Handler(socket));

            // 统计客户端的数量
            count++;
            System.out.println("客户端的数量：" + count);
            // 客户端ip地址
            InetAddress address = socket.getInetAddress();
            System.out.println("当前客户端的IP:端口号：" + address.getHostAddress() + ":" + socket.getPort());
        }
    }


    public static void main(String[] args) throws IOException {
        new ServerThreadPool().service();
    }
}
