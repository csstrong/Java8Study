package com.chensi.netty.mknetty.two;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.nio.charset.Charset;

/*
 * @author  chensi
 * @date  2022/9/13
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    //对于每个传入的消息都要调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received: " + in.toString(Charset.defaultCharset()));
        ctx.write(in);
    }

    //通知ChannelInboundHandler最后一次对channelRead()的调用是当前批量读取中的最后一条消息
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
            .addListener(ChannelFutureListener.CLOSE);
    }

    //在读取操作期间，有异常抛出时会调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
