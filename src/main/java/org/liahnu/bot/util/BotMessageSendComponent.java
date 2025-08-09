package org.liahnu.bot.util;

import com.mikuac.shiro.core.Bot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    public ThreadPoolExecutor createThreadPoolTaskScheduler() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 60, TimeUnit.SECONDS
                , new LinkedBlockingQueue<>(1000)
                , new ThreadPoolExecutor.CallerRunsPolicy()
        );
        log.info("[BotMessageSendComponent] 初始化线程池，核心线程数[{}]，最大线程数[{}]，线程keepalive时间[{}]，队列容量[{}]",
                executor.getCorePoolSize(), executor.getMaximumPoolSize(), executor.getKeepAliveTime(TimeUnit.SECONDS), executor.getQueue().size());
        return executor;
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
