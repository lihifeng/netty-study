package com.hifeng.netty.study.samples.client;

import com.hifeng.netty.study.samples.protocol.Packet;
import com.hifeng.netty.study.samples.protocol.PacketCodeC;
import com.hifeng.netty.study.samples.protocol.request.LoginRequestPacket;
import com.hifeng.netty.study.samples.protocol.response.LoginResponsePacket;
import com.hifeng.netty.study.samples.protocol.response.MessageResponsePacket;
import com.hifeng.netty.study.samples.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Date;
import java.util.UUID;

/**
 * @author lzh
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date()+": 客户端开始登录......");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("zhangsan");
        loginRequestPacket.setPassword("123456");
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        ctx.channel().writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket)packet;
            if(loginResponsePacket.isSuccess()){
                System.out.println(new Date()+": 客户端登录成功");
                LoginUtil.markAsLogin(ctx.channel());
            }else{
                System.out.println(new Date()+": 客户端登录失败，原因:"+loginResponsePacket.getReason());
            }
        } else if(packet instanceof MessageResponsePacket){
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket)packet;
            System.out.println(new Date()+": 收到服务端的消息: "+messageResponsePacket.getMessage());
        }
    }
}
