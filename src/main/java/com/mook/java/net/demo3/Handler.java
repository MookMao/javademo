package com.mook.java.net.demo3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @Author: maojunkai
 * @Date: 2018/11/28 上午10:55
 * @Description:
 */
public class Handler implements Runnable {
    Socket socket = null;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
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

            out.writeUTF("欢迎您！");

            out.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
