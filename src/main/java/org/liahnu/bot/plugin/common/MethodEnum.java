package org.liahnu.bot.plugin.common;

import com.mikuac.shiro.annotation.AnyMessageHandler;
import com.mikuac.shiro.annotation.GroupMessageHandler;
import com.mikuac.shiro.annotation.PrivateMessageHandler;
import com.mikuac.shiro.dto.event.message.AnyMessageEvent;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.dto.event.message.MessageEvent;
import com.mikuac.shiro.dto.event.message.PrivateMessageEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.annotation.Annotation;

/**
 * @author lihanyu
 */

@Getter
@AllArgsConstructor
public enum MethodEnum {
    PRIVATE("private", "私聊", PrivateMessageHandler.class, PrivateMessageEvent.class),

    GROUP("group", "群聊", GroupMessageHandler.class, GroupMessageEvent.class),

    ANY("any", "任意", AnyMessageHandler.class, AnyMessageEvent.class),

    NONE("none", "无", null, null);

    private final String type;

    private final String description;

    private final Class<? extends Annotation> clazz;

    private final Class<? extends MessageEvent> eventClass;


}
