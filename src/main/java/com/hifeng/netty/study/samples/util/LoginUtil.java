package com.hifeng.netty.study.samples.util;

import com.hifeng.netty.study.samples.attribute.Attributes;
import io.netty.channel.Channel;

/**
 * @author lzh
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        return channel.attr(Attributes.LOGIN).get() != null;
    }
}
