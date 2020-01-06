package com.hifeng.netty.study.im.protocol.request;

import com.hifeng.netty.study.im.protocol.Packet;

import static com.hifeng.netty.study.im.protocol.command.Command.HEARTBEAT_REQUEST;

/**
 * @author lzh
 */
public class HeartbeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
