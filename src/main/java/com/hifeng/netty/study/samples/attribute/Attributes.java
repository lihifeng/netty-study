package com.hifeng.netty.study.samples.attribute;

import com.hifeng.netty.study.samples.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author lzh
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
