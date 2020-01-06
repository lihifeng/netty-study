package com.hifeng.netty.study.im.server;

import com.hifeng.netty.study.im.codec.PacketCodecHandler;
import com.hifeng.netty.study.im.codec.Spliter;
import com.hifeng.netty.study.im.handler.IMIdleStateHandler;
import com.hifeng.netty.study.im.server.handler.AuthHandler;
import com.hifeng.netty.study.im.server.handler.HeartbeatRequestHandler;
import com.hifeng.netty.study.im.server.handler.IMHandler;
import com.hifeng.netty.study.im.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @author lzh
 */
public class NettyServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        ch.pipeline().addLast(HeartbeatRequestHandler.INSTANCE);
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });
        bind(bootstrap, PORT);
    }

    private static void bind(final ServerBootstrap bootstrap, final int port) {
        bootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功");
            } else {
                System.err.println(new Date() + ": 端口[" + port + "]绑定失败");
            }
        });
    }
}
