package com.hifeng.netty.study.im.server.handler;

import com.hifeng.netty.study.im.protocol.request.CreateGroupRequestPacket;
import com.hifeng.netty.study.im.protocol.response.CreateGroupResponsePacket;
import com.hifeng.netty.study.im.session.Session;
import com.hifeng.netty.study.im.util.IDUtils;
import com.hifeng.netty.study.im.util.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 */
@Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();
        List<String> userNameList = new ArrayList<>();
        Session session = SessionUtils.getSession(ctx.channel());
        if(!userIdList.contains(session.getUserId())){
            userIdList.add(session.getUserId());
        }
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());
        Channel channel;
        for (String userId : userIdList) {
            channel = SessionUtils.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtils.getSession(channel).getUserName());
            }
        }

        String groupId = IDUtils.randomId();

        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setUserNameList(userNameList);

        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.println("群创建成功，id为[" + createGroupResponsePacket.getGroupId() + "]");
        System.out.println("群里面有： " + createGroupResponsePacket.getUserNameList());

        SessionUtils.bindChannelGroup(groupId, channelGroup);
    }

}
