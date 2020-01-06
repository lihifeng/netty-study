package com.hifeng.netty.study.im.server.handler;

import com.hifeng.netty.study.im.protocol.request.GroupMessageRequestPacket;
import com.hifeng.netty.study.im.protocol.response.GroupMessageResponsePacket;
import com.hifeng.netty.study.im.util.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author lzh
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
        String groupId = requestPacket.getToGroupId();

        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setFromUser(SessionUtils.getSession(ctx.channel()));
        responsePacket.setMessage(requestPacket.getMessage());

        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePacket);
    }
}
