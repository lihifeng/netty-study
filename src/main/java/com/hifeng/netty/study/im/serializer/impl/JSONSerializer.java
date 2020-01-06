package com.hifeng.netty.study.im.serializer.impl;

import com.alibaba.fastjson.JSON;
import com.hifeng.netty.study.im.serializer.Serializer;
import com.hifeng.netty.study.im.serializer.SerializerAlgorithm;

/**
 * @author lzh
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
