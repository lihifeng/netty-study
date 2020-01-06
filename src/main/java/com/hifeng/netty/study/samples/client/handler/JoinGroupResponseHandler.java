package com.hifeng.netty.study.samples.client.handler;

import com.hifeng.netty.study.samples.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lzh
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if(joinGroupResponsePacket.isSuccess()){
            System.out.println("加入群["+joinGroupResponsePacket.getGroupId()+"]成功！");
        }else{
            System.err.println("加入群["+joinGroupResponsePacket.getGroupId()+"]失败，原因为："+joinGroupResponsePacket.getReason());
        }
    }
}
