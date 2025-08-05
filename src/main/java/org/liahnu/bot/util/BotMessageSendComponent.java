package org.liahnu.bot.util;

import com.mikuac.shiro.core.Bot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

/**
 * @author lihanyu
 */
@Slf4j
@Component
public class BotMessageSendComponent {

    @Autowired
    private SignSendMessageComponent botContainer;

    // 自定义线程池
    @Bean("BotMessageThreadPool")
    public ThreadPoolTaskScheduler createThreadPoolTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("bot-message-send-thread-");
        scheduler.initialize();
        return scheduler;
    }


    @Async("BotMessageThreadPool")
    public void sendGroupMsg(Long groupId, String message, boolean isMarkdown) {
        // 发送消息的逻辑
        log.info("[BotMessageSendComponent] 发送群消息[{}]，内容[{}]", groupId, message);
        Bot bot = botContainer.getBot();
        bot.sendGroupMsg(groupId, message, isMarkdown);
    }

    @Async("BotMessageThreadPool")
    public void sendPrivateMsg(Long userId, String message, boolean isMarkdown) {
        // 发送消息的逻辑
        log.info("[BotMessageSendComponent] 发送私聊消息[{}]，内容[{}]", userId, message);
        Bot bot = botContainer.getBot();
        bot.sendPrivateMsg(userId, message, isMarkdown);
    }


}
