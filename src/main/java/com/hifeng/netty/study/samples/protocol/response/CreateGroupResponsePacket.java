package com.hifeng.netty.study.samples.protocol.response;

import com.hifeng.netty.study.samples.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.hifeng.netty.study.samples.protocol.command.Command.CREATE_GROUP_RESPONSE;

/**
 * @author lzh
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;
    private String groupId;
    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
