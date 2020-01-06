package com.hifeng.netty.study.im.protocol.response;

import com.hifeng.netty.study.im.protocol.Packet;
import lombok.Data;

import static com.hifeng.netty.study.im.protocol.command.Command.QUIT_GROUP_RESPONSE;

/**
 * @author lzh
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private String groupId;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
