package com.hifeng.netty.study.im.client.handler;

import com.hifeng.netty.study.im.protocol.response.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lzh
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersResponsePacket listGroupMembersResponsePacket) throws Exception {
        System.out.println("群["+listGroupMembersResponsePacket.getGroupId()+"]中的人包括："+listGroupMembersResponsePacket.getSessionList());
    }
}
