package com.hifeng.netty.study.samples.protocol.request;

import com.hifeng.netty.study.samples.protocol.Packet;
import lombok.Data;

import static com.hifeng.netty.study.samples.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author lzh
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
