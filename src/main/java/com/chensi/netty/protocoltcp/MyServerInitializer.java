package com.chensi.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/*
 * @author  chensi
 * @date  2022/7/18
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //解码器
        pipeline.addLast(new MyMessageDecoder());
        //编码器
        pipeline.addLast(new MyMessageEncoder());
        pipeline.addLast(new MyServerHandler());
    }
}
