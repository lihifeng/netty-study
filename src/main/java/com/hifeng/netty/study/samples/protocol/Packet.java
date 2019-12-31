package com.hifeng.netty.study.samples.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author lzh
 */
@Data
public abstract class Packet {
    /**
     * 通讯协议版本
     */
    @JSONField(serialize = false, deserialize = false)
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
