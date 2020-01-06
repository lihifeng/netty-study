package com.hifeng.netty.study.im.server.handler;

import com.hifeng.netty.study.im.util.SessionUtils;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lzh
 */
@Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!SessionUtils.hasLogin(ctx.channel())){
            ctx.channel().close();
        }else{
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(SessionUtils.hasLogin(ctx.channel())){
            System.out.println("当前连接登录验证完毕， 无需再次认证，AuthHandler被移除");
        }else {
            System.out.println("无登录验证，强制关闭连接！");
        }
    }
}
