package com.hifeng.netty.study.im.protocol.response;

import com.hifeng.netty.study.im.protocol.Packet;
import lombok.Data;

import static com.hifeng.netty.study.im.protocol.command.Command.LOGOUT_RESPONSE;

/**
 * @author lzh
 */
@Data
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
