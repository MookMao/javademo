package com.mook.java.nio;

/**
 * @Author: maojunkai
 * @Date: 2018/12/11 下午9:22
 * @Description:
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws IOException {

        // 单线程
        new Thread(new ReactorTask(1234)).start();

    }

    public static class ReactorTask implements Runnable {

        private Selector selector;

        public ReactorTask(int port) {
            try {
                // 第一步：打开ServerSocketChannel，用于监听客户端的连接，它是所有客户端连接的父管道
                ServerSocketChannel acceptorSvr = ServerSocketChannel.open();

                // 第二步：绑定监听端口，设置连接为非阻塞模式
                acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("localhost"), port));
                acceptorSvr.configureBlocking(false);

                // 第三步：创建Reactor线程，创建多路复用器并启动线程
                selector = Selector.open();

                // 第四步：将ServerSocketChannel注册到Reactor线程的多路复用器Selector上，监听Accept事件
                // register() 的第一个参数总是这个 Selector。第二个参数是 OP_ACCEPT，这里它指定我们想要监听 accept 事件，也就是在新的连接建立时所发生的事件。这是适用于 ServerSocketChannel 的唯一事件类型。
                acceptorSvr.register(selector, SelectionKey.OP_ACCEPT);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            // 第五步：在run方法中无限循环体内轮询准备就绪的Key
            while (true) {
                try {
                    // 在这里会阻塞,无论是连接还是客户端发送数据还是客户端关闭,这里都会触发
                    // 这个方法会阻塞，直到至少有一个已注册的事件发生。当一个或者更多的事件发生时， select() 方法将返回所发生的事件的数量。
                    selector.select();
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectedKeys.iterator();
                    SelectionKey key = null;
                    while (it.hasNext()) {
                        key = it.next();
                        // 移除,防止重复处理
                        it.remove();
                        try {
                            if (key.isValid()) {
                                // 处理新接入的请求消息
                                // 不能使用if else判断，因为一个通道可以关注多个事件
                                if (key.isAcceptable()) {
                                    // 第六步：多路复用器监听到有新的客户端接入，处理新的接入请求，完成TCP三次握手，建立物理链路
                                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                                    SocketChannel sc = ssc.accept();
                                    // 第七步：设置客户端链路为非阻塞模式
                                    sc.configureBlocking(false);
                                    sc.socket().setReuseAddress(true);
                                    // 第八步：将新接入的客户端连接注册到Reactor线程的多路复用器上，监听读操作，读取客户端发送的网络消息
                                    sc.register(selector, SelectionKey.OP_READ);
                                }
                                if (key.isReadable()) {   // handler线程处理
                                    // 第九步：异步读取客户端请求消息到缓存区
                                    SocketChannel sc = (SocketChannel) key.channel();
                                    // 每次新开拓缓冲区
                                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                                    int readBytes = sc.read(readBuffer);

                                    // 第十步：对ByteBuffer进行编解码，如果有半包消息指针reset，继续读取后续的报文
                                    // 非阻塞模式下,read()方法在尚未读取到任何数据时可能就返回了。所以需要关注它的int返回值，它会告诉你读取了多少字节。
                                    if (readBytes > 0) {
                                        // 将Buffer从写模式切换到读模式
                                        readBuffer.flip();
                                        byte[] bytes = new byte[readBuffer.remaining()];
                                        readBuffer.get(bytes);
                                        String body = new String(bytes, "UTF-8");
                                        System.out.println("The time server receive order : " + body);
                                        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                                                ? new java.util.Date(System.currentTimeMillis()).toString()
                                                : "BAD ORDER";
                                        //写应答
                                        byte[] bytes2 = currentTime.getBytes();
                                        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes2.length);
                                        writeBuffer.put(bytes2);
                                        // 将Buffer从写模式切换到读模式
                                        writeBuffer.flip();
                                        // write()方法无法保证能写多少字节到SocketChannel,所以重复调用write()直到Buffer没有要写的字节为止。
                                        // 非阻塞模式下，write()方法在尚未写出任何内容时可能就返回了。所以需要在循环中调用write()
                                        while (writeBuffer.hasRemaining()) {
                                            sc.write(writeBuffer);
                                        }
                                    } else if (readBytes < 0) {
                                        // 对端链路关闭
                                        key.cancel();
                                        sc.close();
                                    } else
                                        ; // 读到0字节，忽略
                                }
                            }
                        } catch (Exception e) {
                            if (key != null) {
                                key.cancel();
                                if (key.channel() != null)
                                    key.channel().close();
                            }
                        }
                    }

                    selectedKeys.clear();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

        }

    }

}
