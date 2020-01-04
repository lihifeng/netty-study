package com.hifeng.netty.study.samples.client;

import com.hifeng.netty.study.samples.client.handler.LoginResponseHandler;
import com.hifeng.netty.study.samples.client.handler.MessageResponseHandler;
import com.hifeng.netty.study.samples.codec.PacketDecoder;
import com.hifeng.netty.study.samples.codec.PacketEncoder;
import com.hifeng.netty.study.samples.protocol.request.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author lzh
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);

//                for (int i = 0; i < 1000; i++) {
//                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
//                    messageRequestPacket.setMessage("你好，欢迎关注我的微信公众号，《geeker90的博客》!");
//                    channel.writeAndFlush(messageRequestPacket);
//                }
            } else if (retry == 0) {
                System.err.println(new Date() + ": 重试次数已用完，放弃连接");
            } else {
                // 1 2 4 8 16
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << (order - 1);
                System.err.println(new Date() + ": 连接失败，第" + order + "次连接(delay:" + delay + ")......");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        new Thread(()->{
            while(!Thread.interrupted()){
                System.out.println("输入消息发送至服务端：");
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                messageRequestPacket.setMessage(line);
                channel.writeAndFlush(messageRequestPacket);
            }
        }).start();
    }

}
