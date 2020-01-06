package com.hifeng.netty.study.samples.protocol.request;

import com.hifeng.netty.study.samples.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.hifeng.netty.study.samples.protocol.command.Command.CREATE_GROUP_REQUEST;

/**
 * @author lzh
 */
@Data
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
