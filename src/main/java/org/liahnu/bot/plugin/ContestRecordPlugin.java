package org.liahnu.bot.plugin;

import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.PrivateMessageHandler;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.dto.event.message.PrivateMessageEvent;
import com.mikuac.shiro.enums.AtEnum;
import lombok.extern.slf4j.Slf4j;
import org.liahnu.bot.biz.BizServiceTemplate;
import org.liahnu.bot.biz.base.BizServiceTypeEnum;
import org.liahnu.bot.biz.request.record.AddContestRecordBizServiceRequest;
import org.liahnu.bot.biz.result.record.AddContestRecordBizServiceResult;
import org.liahnu.bot.model.domain.Elo;
import org.liahnu.bot.model.type.DirectionType;
import org.liahnu.bot.model.vo.UserRecordVO;
import org.liahnu.bot.service.ContestRecordService;
import org.liahnu.bot.service.EloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Matcher;

@Shiro
@Component
@Slf4j
public class ContestRecordPlugin {

    @Autowired
    private ContestRecordService contestRecordService;

    @Autowired
    private EloService eloService;

    @Autowired
    private BizServiceTemplate bizServiceTemplate;

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
        AddContestRecordBizServiceRequest request = new AddContestRecordBizServiceRequest();
        request.setContestId(contestId);
        request.setDirection(DirectionType.getDirectionType(direction));
        request.setScore(score);
        request.setUserId(event.getUserId());
        request.setGroupId(event.getGroupId());

        AddContestRecordBizServiceResult result = bizServiceTemplate.execute(request, BizServiceTypeEnum.ADD_RECORD);

        MsgUtils builder = MsgUtils.builder();
        builder.reply(event.getMessageId());
        builder.text("✅ 已成功添加记录：\n");
        builder.text("| 字段       | 内容           |\n");
        builder.text("|------------|----------------|\n");
        builder.text("| 比赛ID     | " + result.getRecord().getContestId() + " |\n");
        builder.text("| 记录ID     | " + result.getRecord().getId() + " |\n");
        builder.text("| 方向       | " +  result.getRecord().getDirection().getDirection() + " |\n");
        builder.text("| 分数       | " +  result.getRecord().getPoint() + " |\n");
        bot.sendGroupMsg(event.getGroupId(), builder.build(), false);
    }

    @PrivateMessageHandler
    @MessageHandlerFilter(cmd = "更新比赛 (?<contestId>\\d+)")
    public void updateContest(Bot bot, PrivateMessageEvent event, Matcher matcher) {
        String contestIdStr = matcher.group("contestId");
        contestRecordService.calculateScore(Integer.valueOf(contestIdStr));
    }

    @PrivateMessageHandler
    @MessageHandlerFilter(cmd = "查询记录")
    public void getRecord(Bot bot, PrivateMessageEvent event) {
        Long userId = event.getUserId();

        List<UserRecordVO> recentRecord = contestRecordService.getRecentRecord(userId, 5);
        List<Elo> elo = eloService.queryUserElo(userId);

        MsgUtils builder = MsgUtils.builder();
        builder.reply(event.getMessageId());
        builder.text("用户ID: ").at(userId).text("\n");

        // 输出最近记录
        builder.text("最近比赛记录:\n");
        for (UserRecordVO record : recentRecord) {
            builder.text("- 比赛ID: ").text(record.getContestId().toString())
                    .text(", 类型: ").text(record.getType().getDescription())
                    .text(", 方向: ").text(record.getDirection().getName())
                    .text(", 成绩: ").text(record.getPoint().toString())
                    .text(", 精算点数: ").text(record.getEndPoint().toString())
                    .text(", Elo: ").text(record.getEloChange().toString())
                    .text(", 时间: ").text(record.getTime().toString())
                    .text("\n");
        }
        // 输出 Elo 信息
        builder.text("Elo 评分:\n");
        for (Elo e : elo) {
            builder.text("- 类型: ").text(e.getType().getDescription())
                    .text(", 评分: ").text(e.getElo().toString())
                    .text("\n");
        }

        // 发送消息
        bot.sendPrivateMsg(event.getUserId(), builder.build(),false);

    }



}
