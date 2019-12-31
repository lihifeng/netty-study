package com.hifeng.netty.study.samples.server;

import com.hifeng.netty.study.samples.protocol.Packet;
import com.hifeng.netty.study.samples.protocol.PacketCodeC;
import com.hifeng.netty.study.samples.protocol.request.LoginRequestPacket;
import com.hifeng.netty.study.samples.protocol.request.MessageRequestPacket;
import com.hifeng.netty.study.samples.protocol.response.LoginResponsePacket;
import com.hifeng.netty.study.samples.protocol.response.MessageResponsePacket;
import com.hifeng.netty.study.samples.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.Date;

/**
 * @author lzh
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(new Date()+": 客户端开始登录......");
        ByteBuf byteBuf = (ByteBuf)msg;
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket)packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if(validLogin(loginRequestPacket)){
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date()+": 登录成功");
            }else{
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码校验失败");
                System.out.println(new Date()+": 登录失败");
            }

            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        } else if (packet instanceof MessageRequestPacket){
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket)packet;
            System.out.println(new Date()+": 收到客户端消息: "+ messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复:【"+messageRequestPacket.getMessage()+"】");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean validLogin(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
