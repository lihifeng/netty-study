package com.hifeng.netty.study.im.client.console;

import com.hifeng.netty.study.im.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author lzh
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个群组: ");
        String groupId = scanner.next();
        String message = scanner.next();

        GroupMessageRequestPacket requestPacket = new GroupMessageRequestPacket();
        requestPacket.setToGroupId(groupId);
        requestPacket.setMessage(message);

        channel.writeAndFlush(requestPacket);
    }
}
