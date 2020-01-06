package com.hifeng.netty.study.im.client.handler;

import com.hifeng.netty.study.im.protocol.response.LogoutResponsePacket;
import com.hifeng.netty.study.im.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lzh
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {
        SessionUtils.unbindSession(ctx.channel());
    }
}
