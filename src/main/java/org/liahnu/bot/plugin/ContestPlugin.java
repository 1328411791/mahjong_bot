package org.liahnu.bot.plugin;

import cn.hutool.poi.word.TableUtil;
import com.mikuac.shiro.annotation.AnyMessageHandler;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.enums.AtEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
    @MessageHandlerFilter(at = AtEnum.NEED, cmd = "创建比赛")
    public void createContest(Bot bot, GroupMessageEvent event, Matcher matcher) {
        ContestType type =  ContestType.RCR;

        Contest contest = contestService.createContest(event.getUserId(), event.getGroupId(), type);

        // 展示 创建成功，输出比赛 信息
        String message = "✅ 创建成功，比赛信息如下：\n";
        message += "| 字段       | 内容           |\n";
        message += "|------------|----------------|\n";
        message += "| 比赛ID     | " + contest.getId() + " |\n";
        message += "| 比赛类型   | " + contest.getType() + " |\n";
        message += "| 比赛状态   | " + contest.getStatus() + " |\n";

        bot.sendGroupMsg(event.getGroupId(), message, false);
    }

    @GroupMessageHandler
    @MessageHandlerFilter(at = AtEnum.NEED,cmd = "查询比赛")
     public void getContest(Bot bot, GroupMessageEvent event) {
        // 获取当前群的当前用户创建的比赛
        Integer page = 1;
        Integer size  = 10;
        List<Contest> contests = contestService.queryLastContest4Group(event.getGroupId(), page, size);
         if (contests.isEmpty()) {
            bot.sendGroupMsg(event.getGroupId(), "没有找到任何比赛", false);
            return;
        }
         String message = "| 字段       | 内容           |\n";
          message += "|------------|----------------|\n";
           for (Contest contest : contests) {
              message += "| 比赛ID     | " + contest.getId() + " |\n";
              message += "| 比赛类型   | " + contest.getType() + " |\n";
              message += "| 比赛状态   |  " + contest.getStatus() + " |\n";
          }
        bot .sendGroupMsg(event.getGroupId(), message, false);
    }
}
