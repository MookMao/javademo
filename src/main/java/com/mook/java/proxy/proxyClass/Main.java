package com.mook.java.proxy.proxyClass;

import com.mook.java.proxy.dynamicproxy.Subject;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @Author: maojunkai
 * @Date: 2018/7/7 下午3:42
 * @Description: 查看JDK动态代理运行时生成的委托类的字节码文件
 */
public class Main {
    public static void main(String[] args) {
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", new Class<?>[]{Subject.class});
        String path = "/Users/mook/IdeaProjects/JavaDemo/Proxy.class";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(classFile);
            fileOutputStream.flush();
            System.out.println("代理类class文件写入成功");
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("写文件错误");
        } finally {
        }
    }
}
