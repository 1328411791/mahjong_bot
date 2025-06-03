package org.liahnu.bot.plugin;

import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.PrivateMessageHandler;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.dto.event.message.PrivateMessageEvent;
import com.mikuac.shiro.enums.AtEnum;
import lombok.extern.slf4j.Slf4j;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.service.ContestRecordService;
import org.liahnu.bot.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;

@Shiro
@Component
@Slf4j
public class ContestRecordPlugin {

    @Autowired
    private ContestRecordService contestRecordService;

    /*
     * 添加记录
     */
    @GroupMessageHandler
    @MessageHandlerFilter(at = AtEnum.NEED, cmd = "添加记录 (?<contestId>\\d+) (?<direction>\\S+) (?<score>-?\\d+)")
    public void addRecord(Bot bot, GroupMessageEvent event, Matcher matcher) {
        // 使用命名组提取参数
        String contestIdStr = matcher.group("contestId");
        String direction = matcher.group("direction");
        String scoreStr = matcher.group("score");

        Integer contestId = Integer.valueOf(contestIdStr);
        Integer score = Integer.valueOf(scoreStr);

        // 调用 service 添加记录逻辑
        contestRecordService.addRecord(contestId, direction, score,event.getUserId(),event.getGroupId());

        String message = "✅ 已成功添加记录：\n" +
                "| 字段       | 内容           |\n" +
                "|------------|----------------|\n" +
                "| 比赛ID     | " + contestId + " |\n"+
                "| 方向       | " + direction + " |\n" +
                "| 分数       | " + score + " |\n";
        bot.sendGroupMsg(event.getGroupId(), message, false);
    }

    @PrivateMessageHandler
    @MessageHandlerFilter(cmd = "更新比赛 (?<contestId>\\d+)")
    public void updateContest(Bot bot, PrivateMessageEvent event, Matcher matcher) {
        String contestIdStr = matcher.group("contestId");
        contestRecordService.calculateScore(Integer.valueOf(contestIdStr));

    }



}
