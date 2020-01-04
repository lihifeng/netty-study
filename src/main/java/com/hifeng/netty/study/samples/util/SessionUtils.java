package com.hifeng.netty.study.samples.util;

import com.hifeng.netty.study.samples.attribute.Attributes;
import com.hifeng.netty.study.samples.session.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lzh
 */
public class SessionUtils {
    private static final Map<String, Channel> USERID_CHANNEL_MAP = new ConcurrentHashMap<>();

    private SessionUtils() {}

    public static void bindSession(Session session, Channel channel) {
        USERID_CHANNEL_MAP.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel) {
        if(hasLogin(channel)){
            USERID_CHANNEL_MAP.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }

    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return  USERID_CHANNEL_MAP.get(userId);
    }

}
