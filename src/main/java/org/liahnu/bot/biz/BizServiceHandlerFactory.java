package org.liahnu.bot.biz;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.liahnu.bot.biz.base.AbstractBizServiceHandler;
import org.liahnu.bot.biz.base.BizServiceTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
@Component
public class BizServiceHandlerFactory {

    private static final Map<String, AbstractBizServiceHandler> handlerMap = new HashMap<>();


    @Autowired
    public BizServiceHandlerFactory(List<AbstractBizServiceHandler> list) {
        for (AbstractBizServiceHandler handler : list) {
            // 从反射注解获取类型
            BizServiceHandleInterface annotation = AnnotationUtils.findAnnotation(handler.getClass()
                    , BizServiceHandleInterface.class);

            if (annotation == null) {
                log.error("{} 没有注解", handler.getClass());
                throw new RuntimeException("{} 没有注解");
            }

            handlerMap.put(annotation.type().getType(), handler);
        }
    }

    public static AbstractBizServiceHandler getHandler(BizServiceTypeEnum type) {

        Assert.notNull(type, "type 不能为空");

        Assert.isTrue(handlerMap.containsKey(type.getType()), "没有找到对应的处理器");

        return handlerMap.get(type.getType());
    }
}
