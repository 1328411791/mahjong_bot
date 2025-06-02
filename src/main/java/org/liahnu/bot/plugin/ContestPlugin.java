package org.liahnu.bot.plugin;

import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.enums.AtEnum;
import lombok.extern.slf4j.Slf4j;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;

@Shiro
@Component
@Slf4j
public class ContestPlugin {

    @Autowired
    private ContestService contestService;

    /*
     * 创建比赛
     */
    @GroupMessageHandler
    @MessageHandlerFilter(at = AtEnum.NEED, cmd = "创建比赛 \\s(.*)?$")
    public void createContest(Bot bot, GroupMessageEvent event, Matcher matcher) {
        ContestType type = ContestType.valueOf(matcher.group(1));
        Contest contest = contestService.createContest(event.getUserId(), event.getGroupId(), type);

        // 展示 创建成功，输出比赛 信息
         bot.sendGroupMsg(event.getGroupId(), "创建成功，输出比赛信息", false);
         bot.sendGroupMsg(event.getGroupId(), "比赛信息", false);
         bot.sendGroupMsg(event.getGroupId(), "比赛ID：" + contest.getId(), false);
         bot.sendGroupMsg(event.getGroupId(), "比赛类型：" + contest.getType(), false);
         bot.sendGroupMsg(event.getGroupId(), "比赛状态：" + contest.getStatus(), false);
    }
}
