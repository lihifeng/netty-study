package com.hifeng.netty.study.samples.server.handler;

import com.hifeng.netty.study.samples.protocol.request.QuitGroupRequestPacket;
import com.hifeng.netty.study.samples.protocol.response.QuitGroupResponsePacket;
import com.hifeng.netty.study.samples.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author lzh
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String groupId = quitGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        QuitGroupResponsePacket quitGroupResponsePacket =new QuitGroupResponsePacket();
        quitGroupResponsePacket.setSuccess(true);
        quitGroupResponsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
