package com.hifeng.netty.study.im.server.handler;

import com.hifeng.netty.study.im.protocol.request.LoginRequestPacket;
import com.hifeng.netty.study.im.protocol.response.LoginResponsePacket;
import com.hifeng.netty.study.im.session.Session;
import com.hifeng.netty.study.im.util.IDUtils;
import com.hifeng.netty.study.im.util.SessionUtils;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author lzh
 */
@Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        ctx.writeAndFlush(login(ctx, loginRequestPacket));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtils.unbindSession(ctx.channel());
    }

    private LoginResponsePacket login(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());

        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = IDUtils.randomId();
            loginResponsePacket.setUserId(userId);
            SessionUtils.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
            System.out.println("["+loginRequestPacket.getUserName()+"]登录成功");
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账号密码校验失败");
            System.out.println(new Date() + ": 登录失败");
        }
        return loginResponsePacket;
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }


}
