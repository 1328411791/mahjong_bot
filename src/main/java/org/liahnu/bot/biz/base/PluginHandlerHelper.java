package org.liahnu.bot.biz.base;

import org.liahnu.bot.biz.BizServiceHandlerFactory;

/**
 * @author lihanyu
 */
public class PluginHandlerHelper {

    public static <R extends BizServiceBaseResult> R doHandler(BizServiceTypeEnum typeEnum, BizServiceBaseRequest request) {
        @SuppressWarnings("unchecked")
        R result = (R) BizServiceHandlerFactory.getHandler(typeEnum).handle(request);
        return result;
    }
}
