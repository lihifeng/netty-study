package com.hifeng.netty.study.im.server.handler;

import com.hifeng.netty.study.im.protocol.request.MessageRequestPacket;
import com.hifeng.netty.study.im.protocol.response.MessageResponsePacket;
import com.hifeng.netty.study.im.session.Session;
import com.hifeng.netty.study.im.util.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lzh
 */
@Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        Channel toUserChannel = SessionUtils.getChannel(messageRequestPacket.getToUserId());
        if(toUserChannel != null && SessionUtils.hasLogin(toUserChannel)){
            Session session = SessionUtils.getSession(ctx.channel());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setFromUserId(session.getUserId());
            messageResponsePacket.setFromUserName(session.getUserName());
            messageResponsePacket.setMessage(messageRequestPacket.getMessage());

            toUserChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println("["+messageRequestPacket.getToUserId()+"]不在线，发送失败！");
        }

    }

}
