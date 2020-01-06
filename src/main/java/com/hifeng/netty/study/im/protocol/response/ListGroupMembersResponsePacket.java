package com.hifeng.netty.study.im.protocol.response;

import com.hifeng.netty.study.im.protocol.Packet;
import com.hifeng.netty.study.im.session.Session;
import lombok.Data;

import java.util.List;

import static com.hifeng.netty.study.im.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @author lzh
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;
    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
