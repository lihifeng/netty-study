package com.hifeng.netty.study.im.protocol.response;

import com.hifeng.netty.study.im.protocol.Packet;

import static com.hifeng.netty.study.im.protocol.command.Command.HEARTBEAT_RESPONSE;

/**
 * @author lzh
 */
public class HeartbeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
