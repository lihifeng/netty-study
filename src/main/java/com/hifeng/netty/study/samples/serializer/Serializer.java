package com.hifeng.netty.study.samples.serializer;

import com.hifeng.netty.study.samples.serializer.impl.JSONSerializer;

/**
 * @author lzh
 */
public interface Serializer {

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * 对象序列化
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 对象反序列化
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
