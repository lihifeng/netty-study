package com.hifeng.netty.study.samples.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author lzh
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(new Date() + ": 客户端写出数据");
            ByteBuf buffer = getByteBuf(ctx);
            ctx.channel().writeAndFlush(buffer);
        }
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，欢迎使用netty《netty入门到精通》!".getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);
        return buffer;
    }


}
