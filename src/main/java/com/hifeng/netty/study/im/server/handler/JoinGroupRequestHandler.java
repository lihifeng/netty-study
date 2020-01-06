package com.hifeng.netty.study.im.server.handler;

import com.hifeng.netty.study.im.protocol.request.JoinGroupRequestPacket;
import com.hifeng.netty.study.im.protocol.response.JoinGroupResponsePacket;
import com.hifeng.netty.study.im.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author lzh
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);

        ctx.channel().writeAndFlush(responsePacket);
    }
}
