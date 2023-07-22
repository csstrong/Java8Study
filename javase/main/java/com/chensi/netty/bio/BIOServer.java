package com.chensi.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * @author  chensi
 * @date  2022/9/7
 */

public class BIOServer {

    public static void main(String[] args) throws IOException {

        /**
         * 线程池机制
         * 1.创建一个线程池
         * 2.如果有客户端连接，就创建一个线程，与之通讯
         */
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器端启动，等待连接中...");


        while (true) {
            System.out.println("线程信息id = " + Thread.currentThread().getId() + "名字 = " + Thread.currentThread().getName());
            //服务端监听，等待客户端连接
            System.out.println("等待连接....");

            //阻塞在accept()
            final Socket socket = serverSocket.accept();

            //如果有客户端连接，则继续往下处理
            System.out.println("连接到一个客户端");

            //创建一个线程，进行通讯处理
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    //通讯业务逻辑
    private static void handler(Socket socket) {
        try {
            System.out.println("线程信息id是 =" + Thread.currentThread().getId() + "名字 = " + Thread.currentThread().getName());
            byte[] bytes = new byte[1024];

            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            //循环的读取客户端发送的数据
            while (true) {
                System.out.println("线程信息id = " + Thread.currentThread().getId() + "名字 = " + Thread.currentThread().getName());
                System.out.println("read....");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    //输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭与client的链接。");
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
