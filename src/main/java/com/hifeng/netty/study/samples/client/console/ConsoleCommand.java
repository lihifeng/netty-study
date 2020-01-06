package com.hifeng.netty.study.samples.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author lzh
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
