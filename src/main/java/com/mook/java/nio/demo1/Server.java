package com.mook.java.nio.demo1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server {
    // 维护队列
    private static List<SocketChannel> channelList = new ArrayList<>();

    private static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        // 第一步：打开ServerSocketChannel，用于监听客户端的连接，它是所有客户端连接的父管道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 第二步：绑定监听端口，设置连接为非阻塞模式
        serverSocketChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("localhost"), 1234));
        serverSocketChannel.configureBlocking(false);

        while (true) {
            // 轮询SocketChannel队列，看看是否有数据
            Iterator<SocketChannel> iterator = channelList.iterator();
            while (iterator.hasNext()) {
                SocketChannel socketChannel = iterator.next();
                int read = socketChannel.read(byteBuffer);
                if (read > 0) {
                    System.out.println("read ----- :" + read);
                    byteBuffer.flip();
                    byte[] bytes = new byte[read];
                    byteBuffer.get(bytes);
                    String content = new String(bytes);
                    System.out.println(content);
                    byteBuffer.flip();
                } else if (read == -1) {
                    iterator.remove();
                    socketChannel.close();
                }
            }

            SocketChannel newSocketChannel = serverSocketChannel.accept();
            if (newSocketChannel != null) {
                System.out.println("conn success");
                // 设置read为非阻塞模式
                newSocketChannel.configureBlocking(false);
                channelList.add(newSocketChannel);
                System.out.println("channelList size: " + channelList.size());
            }
        }
    }
}
