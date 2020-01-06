package com.hifeng.netty.study.im.server.handler;

import com.hifeng.netty.study.im.protocol.request.HeartbeatRequestPacket;
import com.hifeng.netty.study.im.protocol.response.HeartbeatResponsePacket;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lzh
 */
@Sharable
public class HeartbeatRequestHandler extends SimpleChannelInboundHandler<HeartbeatRequestPacket> {

    public static final HeartbeatRequestHandler INSTANCE = new HeartbeatRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartbeatRequestPacket requestPacket) throws Exception {
        ctx.writeAndFlush(new HeartbeatResponsePacket());
    }
}
