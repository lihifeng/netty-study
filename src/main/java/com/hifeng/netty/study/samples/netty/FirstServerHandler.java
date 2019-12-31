package com.hifeng.netty.study.samples.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author lzh
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf)msg;

        System.out.println(new Date()+": 服务端读到数据->"+byteBuf.toString(Charset.forName("utf-8")));

        System.out.println(new Date() +": 服务端写出数据");
        ByteBuf buffer = getByteBuf(ctx);
        ctx.writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = "你好，欢迎使用netty".getBytes(Charset.forName("utf-8"));

        buffer.writeBytes(bytes);

        return buffer;
    }
}
