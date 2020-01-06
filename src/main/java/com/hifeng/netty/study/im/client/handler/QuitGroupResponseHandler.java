package com.hifeng.netty.study.im.client.handler;

import com.hifeng.netty.study.im.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lzh
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if(quitGroupResponsePacket.isSuccess()){
            System.out.println("退出群聊["+quitGroupResponsePacket.getGroupId()+"]成功！");
        }else{
            System.err.println("退出群聊["+quitGroupResponsePacket.getGroupId()+"]失败！");
        }
    }
}
