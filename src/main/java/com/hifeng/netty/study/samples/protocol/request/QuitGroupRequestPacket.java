package com.hifeng.netty.study.samples.protocol.request;

import com.hifeng.netty.study.samples.protocol.Packet;
import lombok.Data;

import static com.hifeng.netty.study.samples.protocol.command.Command.QUIT_GROUP_REQUEST;

/**
 * @author lzh
 */
@Data
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
