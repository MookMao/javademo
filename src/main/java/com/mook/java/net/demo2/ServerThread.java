package com.mook.java.net.demo2;

import java.io.*;
import java.net.Socket;

/**
 * @Author: maojunkai
 * @Date: 2018/11/27 上午12:39
 * @Description:服务器线程处理类
 */
public class ServerThread extends Thread {
    /**
     * 和本线程相关的Socket
     */
    Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * 线程执行的操作，响应客户端的请求
     */
    @Override
    public void run(){
        DataInputStream input = null;
        DataOutputStream out = null;
        try {
            // 获取输入流，并读取客户端信息
            input = new DataInputStream(socket.getInputStream());
            // read阻塞等待客户端数据
            String clientInputStr = input.readUTF();
            System.out.println("我是服务器，客户端说：" + clientInputStr);

            // 向客户端回复信息
            out = new DataOutputStream(socket.getOutputStream());

            Thread.sleep(10000);

            out.writeUTF("欢迎您！");

            out.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            // 关闭资源
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
