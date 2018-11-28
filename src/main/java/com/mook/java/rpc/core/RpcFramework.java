package com.mook.java.rpc.core;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: maojunkai
 * @Date: 2018/11/4 下午10:46
 * @Description:
 */
public class RpcFramework {
    private final static Integer MAX_PORT = 65535;

    /**
     * 每个CPU拥有的线程数
     */
    private final static Integer POOL_SIZE = 4;

    /**
     * 创建线程池
     */
    private final static ExecutorService exetutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);

    /**
     * 暴露服务
     *
     * @param service 服务实现
     * @param port 服务端口
     * @throws Exception
     */
    public static void export(final Object service, int port) throws Exception {
        if (service == null) {
            throw new IllegalArgumentException("service instance == null");
        }
        if (port <= 0 || port > MAX_PORT) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println("Export service " + service.getClass().getName() + " on port " + port);
        // 绑定IP和端口号，监听客户端在该端口发送的TCP连接请求
        ServerSocket server = new ServerSocket(port);

        // 多线程实现服务器与多客户端之间的通信
        for(;;) {
            try {
                // final 阻塞获取已完成的连接（TCP三次握手已经建立）
                // TODO 服务端的Socket实例和客户端的Socket实例有什么区别？
                final Socket socket = server.accept();
                // 每接受一个请求，就交由线程池负责处理该请求
                exetutorService.execute(() -> {
                        try {
                            try {
                                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                                try {
                                    String methodName = input.readUTF();
                                    System.out.println("server: " + methodName);
                                    Class<?>[] parameterTypes = (Class<?>[])input.readObject();
                                    System.out.println("server " + parameterTypes);
                                    Object[] arguments = (Object[])input.readObject();
                                    System.out.println("server: " + arguments);
                                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                                    try {
                                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                                        Object result = method.invoke(service, arguments);
                                        System.out.println("server: " + result);
                                        output.writeObject(result);
                                    } catch (Throwable t) {
                                        output.writeObject(t);
                                    } finally {
                                        output.close();
                                    }
                                } finally {
                                    input.close();
                                }
                            } finally {
                                socket.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 引用服务
     *
     * @param <T> 接口泛型
     * @param interfaceClass 接口类型
     * @param host 服务器主机名
     * @param port 服务器端口
     * @return 远程服务
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        if (interfaceClass == null) {
            throw new IllegalArgumentException("Interface class == null");
        }
        if (! interfaceClass.isInterface()) {
            throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");
        }
        if (host == null || host.length() == 0) {
            throw new IllegalArgumentException("Host == null!");
        }
        if (port <= 0 || port > MAX_PORT) {
            throw new IllegalArgumentException("Invalid port " + port);
        }
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                // 创建Socket对象，指明需要连接的服务器的地址和端口号 - 客户端阻塞，直到TCP三次握手建立或者出现异常
                Socket socket = new Socket(host, port);
                try {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        output.writeUTF(method.getName());
                        System.out.println("client: " + method.getName());

                        output.writeObject(method.getParameterTypes());
                        System.out.println("client: " + method.getParameterTypes());

                        Thread.sleep(10000);

                        output.writeObject(arguments);
                        System.out.println("client: " + arguments);
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            Object result = input.readObject();
                            System.out.println("client: " + result);
                            if (result instanceof Throwable) {
                                throw (Throwable) result;
                            }
                            return result;
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                } finally {
                    socket.close();
                }
            }
        });
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
