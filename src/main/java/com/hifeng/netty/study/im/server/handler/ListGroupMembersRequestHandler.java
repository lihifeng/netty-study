package com.hifeng.netty.study.im.server.handler;

import com.hifeng.netty.study.im.protocol.request.ListGroupMembersRequestPacket;
import com.hifeng.netty.study.im.protocol.response.ListGroupMembersResponsePacket;
import com.hifeng.netty.study.im.session.Session;
import com.hifeng.netty.study.im.util.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);

        List<Session> sessionList = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtils.getSession(channel);
            sessionList.add(session);
        }

        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        listGroupMembersResponsePacket.setGroupId(groupId);
        listGroupMembersResponsePacket.setSessionList(sessionList);

        ctx.channel().writeAndFlush(listGroupMembersResponsePacket);
    }
}
