package com.hifeng.netty.study.samples.client.console;

import com.hifeng.netty.study.samples.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author lzh
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入groupId,加入群聊：");
        String groupId = scanner.next();
        JoinGroupRequestPacket joinGroupRequestPacket =new JoinGroupRequestPacket();
        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
