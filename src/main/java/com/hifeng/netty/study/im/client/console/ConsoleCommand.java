package com.hifeng.netty.study.im.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author lzh
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
