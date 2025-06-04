package org.liahnu.bot.plugin;

import com.mikuac.shiro.annotation.AnyMessageHandler;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.MessageHandlerFilter;
import com.mikuac.shiro.annotation.common.Shiro;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.AnyMessageEvent;
import com.mikuac.shiro.enums.AtEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Shiro
@Component
@Slf4j
public class HelpPlugin {

    @AnyMessageHandler
    @MessageHandlerFilter(cmd = "help",at = AtEnum.NEED)
    public void help(Bot bot, AnyMessageEvent event) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/help.jpg");
        byte[] img = resource.getContentAsByteArray();
        MsgUtils msg = MsgUtils.builder()
                .reply(event.getMessageId())
                .img(img)
                .text("欢迎使用Mahjong Bot，请输入以下命令：\n")
                .text("- help：显示帮助信息\n")
                .text("私聊功能: \n")
                .text("- 添加记录 <contestId> <direction> <score>：记录比赛成绩\n")
                .text("- 更新比赛 <contestId>：更新特定比赛数据\n")
                .text("- 查询比赛：获取比赛列表\n")
                .text("- 创建比赛 [参数]：创建一个新的比赛\n")
                .text("私聊功能: \n")
                .text("- 查询记录：查看历史比赛记录\n");

        bot.sendMsg(event, msg.build(), false);
    }
}
