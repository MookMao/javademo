package com.mook.java.net.demo2;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author: maojunkai
 * @Date: 2018/11/27 上午12:36
 * @Description:
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("客户端启动...");
        while (true) {
            Socket socket = null;
            DataInputStream input = null;
            DataOutputStream out = null;
            try {
                //1.创建客户端Socket，指定服务器地址和端口
                // connect
                socket = new Socket("localhost", 8886);
                System.out.println("客户端已与(" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + ")建立连接");
                //2.获取输出流，向服务器端发送信息
                out = new DataOutputStream(socket.getOutputStream());

                // 测试服务端read阻塞等待
                Thread.sleep(10000);

                // 一直阻塞到写入操作系统或者w网络IO出现异常
                out.writeUTF("用户名：whf;密码：789");

                //3.获取输入流，并读取服务器端的响应信息
                input = new DataInputStream(socket.getInputStream());
                // read阻塞等待服务端数据
                String ret = input.readUTF();
                System.out.println("我是客户端，服务器说：" + ret);

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //4.关闭资源
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
}
