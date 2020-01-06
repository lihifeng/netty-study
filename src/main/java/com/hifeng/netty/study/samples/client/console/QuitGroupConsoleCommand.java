package com.hifeng.netty.study.samples.client.console;

import com.hifeng.netty.study.samples.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author lzh
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入groupId,退出群聊：");
        String groupId = scanner.next();
        QuitGroupRequestPacket quitGroupRequestPacket =new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
