package com.hifeng.netty.study.im.attribute;

import com.hifeng.netty.study.im.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author lzh
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
