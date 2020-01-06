package com.hifeng.netty.study.im.protocol.request;

import com.hifeng.netty.study.im.protocol.Packet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.hifeng.netty.study.im.protocol.command.Command.MESSAGE_REQUEST;

/**
 * @author lzh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserId;
    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
