package com.hifeng.netty.study.im.util;

import com.hifeng.netty.study.im.attribute.Attributes;
import com.hifeng.netty.study.im.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lzh
 */
public class SessionUtils {
    private static final Map<String, Channel> USERID_CHANNEL_MAP = new ConcurrentHashMap<>();
    private static final Map<String, ChannelGroup> GROUPID_CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();

    private SessionUtils() {}

    public static void bindSession(Session session, Channel channel) {
        USERID_CHANNEL_MAP.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unbindSession(Channel channel) {
        if(hasLogin(channel)){
            Session session = getSession(channel);
            USERID_CHANNEL_MAP.remove(session.getUserId());
            channel.attr(Attributes.SESSION).set(null);
            System.out.println(session+"退出登录!");
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

    public static void bindChannelGroup(String groupId, ChannelGroup channelGroup) {
        GROUPID_CHANNEL_GROUP_MAP.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(String groupId) {
        return GROUPID_CHANNEL_GROUP_MAP.get(groupId);
    }

}
