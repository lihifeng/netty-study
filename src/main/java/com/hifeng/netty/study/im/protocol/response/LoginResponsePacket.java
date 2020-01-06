package com.hifeng.netty.study.im.protocol.response;

import com.hifeng.netty.study.im.protocol.Packet;
import lombok.Data;

import static com.hifeng.netty.study.im.protocol.command.Command.LOGIN_RESPONSE;

/**
 * @author lzh
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String reason;
    private String userId;
    private String userName;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
