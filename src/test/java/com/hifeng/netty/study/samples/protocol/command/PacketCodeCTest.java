package com.hifeng.netty.study.samples.protocol.command;

import com.hifeng.netty.study.samples.protocol.Packet;
import com.hifeng.netty.study.samples.protocol.PacketCodeC;
import com.hifeng.netty.study.samples.protocol.request.LoginRequestPacket;
import com.hifeng.netty.study.samples.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author lzh
 */
class PacketCodeCTest {

    @Test
    void encode() {
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//        loginRequestPacket.setVersion((byte)1);
//        loginRequestPacket.setUserId("123");
//        loginRequestPacket.setUserName("zhangsan");
//        loginRequestPacket.setPassword("123456");
//
//        PacketCodeC packetCodeC = new PacketCodeC();
//        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
//        Packet decodePacket = packetCodeC.decode(byteBuf);
//
//        Assertions.assertArrayEquals(Serializer.DEFAULT.serialize(loginRequestPacket), Serializer.DEFAULT.serialize(decodePacket));
    }

}