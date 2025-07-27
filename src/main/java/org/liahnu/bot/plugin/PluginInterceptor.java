package org.liahnu.bot.plugin;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotMessageEventInterceptor;
import com.mikuac.shiro.dto.event.message.MessageEvent;
import com.mikuac.shiro.exception.ShiroException;
import org.springframework.stereotype.Component;

@Component
public class PluginInterceptor implements BotMessageEventInterceptor {
    @Override
    public boolean preHandle(Bot bot, MessageEvent event) throws ShiroException {
        return false;
    }

    @Override
    public void afterCompletion(Bot bot, MessageEvent event) throws ShiroException {
        // 异常处理



    }
}
