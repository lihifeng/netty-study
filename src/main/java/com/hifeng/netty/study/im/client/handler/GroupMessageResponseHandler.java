package com.hifeng.netty.study.im.client.handler;

import com.hifeng.netty.study.im.protocol.response.GroupMessageResponsePacket;
import com.hifeng.netty.study.im.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lzh
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket) throws Exception {
        String fromGroupId =responsePacket.getFromGroupId();
        Session fromUser = responsePacket.getFromUser();
        System.out.println("收到群["+fromGroupId+"]中["+fromUser+"]发来的消息: "+ responsePacket.getMessage());
    }
}
