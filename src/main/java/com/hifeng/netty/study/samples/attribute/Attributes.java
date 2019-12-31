package com.hifeng.netty.study.samples.attribute;

import io.netty.util.AttributeKey;

/**
 * @author lzh
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
