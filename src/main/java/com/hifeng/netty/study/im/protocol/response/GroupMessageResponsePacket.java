package com.hifeng.netty.study.im.protocol.response;

import com.hifeng.netty.study.im.protocol.Packet;
import com.hifeng.netty.study.im.session.Session;
import lombok.Data;

import static com.hifeng.netty.study.im.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @author lzh
 */
@Data
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;
    private Session fromUser;
    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
