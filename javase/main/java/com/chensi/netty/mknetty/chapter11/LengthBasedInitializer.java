package com.chensi.netty.mknetty.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/*
 * @author  chensi
 * @date  2022/10/14
 */
public class LengthBasedInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch)throws Exception{
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(
            64*1024,0,8));
        //添加FrameHandler以处理每个帧
        pipeline.addLast(new FrameHandler());
    }

    public static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf>{
        @Override
        public void channelRead0(ChannelHandlerContext ctx,ByteBuf msg)throws Exception{
            //do something
        }
    }
}
