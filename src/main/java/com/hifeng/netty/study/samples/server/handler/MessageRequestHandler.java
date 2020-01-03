package com.hifeng.netty.study.samples.server.handler;

import com.hifeng.netty.study.samples.protocol.request.MessageRequestPacket;
import com.hifeng.netty.study.samples.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author lzh
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        ctx.channel().writeAndFlush(receiveMessage(messageRequestPacket));
    }

    private MessageResponsePacket receiveMessage(MessageRequestPacket messageRequestPacket) {
        System.out.println(new Date()+": 收到客户端消息: "+ messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage("服务端回复:【"+messageRequestPacket.getMessage()+"】");
        return messageResponsePacket;
    }

}
