package com.hifeng.netty.study.im.client.handler;

import com.hifeng.netty.study.im.protocol.request.HeartbeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author lzh
 */
public class HeartbeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartbeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartbeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(()->{
            if(ctx.channel().isActive()){
                ctx.writeAndFlush(new HeartbeatRequestPacket());
                scheduleSendHeartbeat(ctx);
            }
        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
    }
}
