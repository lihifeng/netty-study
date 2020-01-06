package com.hifeng.netty.study.samples.server.handler;

import com.hifeng.netty.study.samples.protocol.request.CreateGroupRequestPacket;
import com.hifeng.netty.study.samples.protocol.response.CreateGroupResponsePacket;
import com.hifeng.netty.study.samples.util.IDUtils;
import com.hifeng.netty.study.samples.util.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();
        List<String> userNameList = new ArrayList<>();

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
