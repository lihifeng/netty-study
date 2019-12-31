package com.hifeng.netty.study.samples.protocol.request;

import com.hifeng.netty.study.samples.protocol.Packet;
import lombok.Data;

import static com.hifeng.netty.study.samples.protocol.command.Command.LOGIN_REQUEST;

/**
 * @author lzh
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
