package com.hifeng.netty.study.samples.client.console;

import com.hifeng.netty.study.samples.protocol.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author lzh
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();

        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();
        listGroupMembersRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
