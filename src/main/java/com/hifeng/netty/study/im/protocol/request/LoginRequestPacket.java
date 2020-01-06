package com.hifeng.netty.study.im.protocol.request;

import com.hifeng.netty.study.im.protocol.Packet;
import lombok.Data;

import static com.hifeng.netty.study.im.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author lzh
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
