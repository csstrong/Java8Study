package com.chensi.netty.nio.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/*
 * @author  chensi
 * @date  2022/7/15
 */
public class NewIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 7001));
        String filePath = "src/main/java/com/chensi/netty/nio/zerocopy/video.mp4";
        //文件channel
        FileChannel fileChannel = new FileInputStream(filePath).getChannel();

        /**
         *         //在 linux 下一个 transferTo 方法就可以完成传输
         *         //在 windows 下一次调用 transferTo 只能发送 8m, 就需要分段传输文件,而且要主要
         *         //传输时的位置=》课后思考...
         *         //transferTo 底层使用到零拷贝
         */
        long startTime = System.currentTimeMillis();
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        //8m=8388608
        System.out.println("transform size count:" + transferCount + ",count time:" + (System.currentTimeMillis() - startTime) + "ms");

        fileChannel.close();
    }
}
