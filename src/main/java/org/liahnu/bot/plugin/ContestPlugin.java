package org.liahnu.bot.plugin;

import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.enums.AtEnum;
import lombok.extern.slf4j.Slf4j;
import org.liahnu.bot.biz.BizServiceException;
import org.liahnu.bot.biz.PluginBizServiceTemplate;
import org.liahnu.bot.biz.base.BizServiceTypeEnum;
import org.liahnu.bot.biz.base.ServiceCallback;
import org.liahnu.bot.biz.request.contest.CreateContestBizServiceRequest;
import org.liahnu.bot.biz.result.contest.CreateContestBizServiceResult;
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

    @Autowired
    private PluginBizServiceTemplate pluginBizServiceTemplate;

    /*
     * 创建比赛
     */
    @GroupMessageHandler
    @MessageHandlerFilter(at = AtEnum.NEED, cmd = "创建比赛\\s(.*)?")
    public void createContest(Bot bot, GroupMessageEvent event, Matcher matcher) {
        pluginBizServiceTemplate.execute(
                BizServiceTypeEnum.CREATE_CONTEST, new ServiceCallback<CreateContestBizServiceRequest, CreateContestBizServiceResult>() {
                    @Override
                    public CreateContestBizServiceRequest buildRequest() {
                        // 解析比赛类型
                        String stringType = matcher.group(1);
                        ContestType type = ContestType.valueOf(stringType);

                        // 构建请求
                        CreateContestBizServiceRequest request = new CreateContestBizServiceRequest();
                        request.setGroupId(event.getGroupId());
                        request.setUserId(event.getUserId());
                        request.setContestType(type);
                        return request;
                    }

                    @Override
                    public void success(CreateContestBizServiceResult result) {
                        // 展示 创建成功，输出比赛 信息
                        MsgUtils builder = MsgUtils.builder();
                        builder.reply(event.getMessageId());
                        builder.text("✅ 创建成功，比赛信息如下：\n");
                        builder.text("| 字段       | 内容          |\n");
                        builder.text("|-----------|--------------|\n");
                        builder.text("| 比赛ID     | " + result.getContest().getId() + " |\n");
                        builder.text("| 比赛类型   | " + result.getContest().getType() + " |\n");
                        builder.text("| 比赛状态   | " + result.getContest().getStatus() + " |\n");

                        bot.sendGroupMsg(event.getGroupId(), builder.build(), false);
                    }

                    @Override
                    public void fail(CreateContestBizServiceRequest request, BizServiceException e) {
                        bot.sendGroupMsg(event.getGroupId(), "创建失败，原因：" + e.getMessage(), false);
                    }
                });
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

        MsgUtils builder = MsgUtils.builder();
         builder.reply(event.getMessageId());
         builder.text(" | 字段       | 内容           |\n");
        builder.text("|------------|----------------|\n");
           for (Contest contest : contests) {
               builder.text("| 比赛ID     | " + contest.getId() + " |\n");
               builder.text("| 比赛类型   | " + contest.getType() + " |\n");
               builder.text("| 比赛状态   |  " + contest.getStatus() + " |\n");
          }
        bot .sendGroupMsg(event.getGroupId(), builder.build(), false);
    }
}
