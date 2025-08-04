package org.liahnu.bot.util;

import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.core.BotContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lihanyu
 */
@Component
public class SignSendMessageComponent {


    private final AtomicReference<Bot> botRef = new AtomicReference<>();

    @Autowired
    private BotContainer botContainer;

    @Value("${shiro.bots.bot-id}")
    private Long botId;

    public Bot getBot(){
        Bot bot = botRef.get();
        if (bot == null) {
            synchronized (this) {
                bot = botRef.get();
                if (bot == null) {
                    bot = botContainer.robots.get(botId);
                    if(bot == null){
                        throw new RuntimeException("Bot not found");
                    }
                    botRef.set(bot);
                }
            }
        }
        return bot;
    }

}
