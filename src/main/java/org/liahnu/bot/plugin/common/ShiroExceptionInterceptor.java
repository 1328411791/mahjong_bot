package org.liahnu.bot.plugin.common;


import com.mikuac.shiro.core.Bot;
import com.mikuac.shiro.dto.event.message.AnyMessageEvent;
import com.mikuac.shiro.dto.event.message.GroupMessageEvent;
import com.mikuac.shiro.dto.event.message.PrivateMessageEvent;
import com.mikuac.shiro.exception.ShiroException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.liahnu.bot.biz.BizServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lihanyu
 * @description com.mikuac.shiro.annotation 注解全局异常拦截器
 */
@Slf4j
@Aspect
@Component
@ExceptionHandler
public class ShiroExceptionInterceptor {

    @Pointcut("@annotation(com.mikuac.shiro.annotation.MessageHandlerFilter)")  // 修正注解名称
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        // 从joinpoint获取参数
        Map<Class<?>, Object> argMap = new HashMap<>();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            argMap.put(arg.getClass(), arg);
        }

        // 获取方法上其他注解
        MethodEnum methodType = getMethodType(joinPoint);


        if (!argMap.containsKey(Bot.class)) {
            throw new ShiroException("bot is null");
        }

        try {
            return joinPoint.proceed();
        } catch (BizServiceException e) {
            log.error("[ShiroException] {}", e.getMessage());
            sendException(e, methodType, argMap);
            throw e;
        }
    }

    private void sendException(Throwable e, MethodEnum methodType, Map<Class<?>, Object> argMap) {
        Bot bot = (Bot) argMap.get(Bot.class);

        String msg = String.format("[%s] 插件异常: %s", methodType.getDescription(), e.getMessage());
        switch (methodType) {
            case PRIVATE:
                PrivateMessageEvent privateMessageEvent = (PrivateMessageEvent) argMap.get(PrivateMessageEvent.class);
                bot.sendPrivateMsg(privateMessageEvent.getUserId(), msg, false);
                break;
            case GROUP:
                GroupMessageEvent groupMessageEvent = (GroupMessageEvent) argMap.get(GroupMessageEvent.class);
                bot.sendGroupMsg(groupMessageEvent.getGroupId(), msg, false);
                break;
            case ANY:
                AnyMessageEvent anyMessageEvent = (AnyMessageEvent) argMap.get(AnyMessageEvent.class);
                bot.sendMsg(anyMessageEvent, msg, false);
                break;
            default:
                log.error("[ShiroException] 未知异常类型: {}", methodType);
                break;
        }
    }

    private MethodEnum getMethodType(ProceedingJoinPoint joinPoint) {
        // 获取方法上的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        if (method == null) {
            throw new ShiroException("messageHandlerFilter is null");
        }

        for (MethodEnum methodEnum : MethodEnum.values()) {
            // 跳过NONE
            if (methodEnum == MethodEnum.NONE) {
                continue;
            }
            Annotation annotation = method.getAnnotation(methodEnum.getClazz());
            if (methodEnum.getClazz().isAssignableFrom(annotation.getClass())) {
                return methodEnum;
            }
        }

        // 没有找到匹配的注解，返回NONE
        return MethodEnum.NONE;
    }

}


