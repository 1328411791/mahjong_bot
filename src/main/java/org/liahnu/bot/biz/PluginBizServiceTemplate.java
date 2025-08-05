package org.liahnu.bot.biz;

import org.liahnu.bot.biz.base.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class PluginBizServiceTemplate {

    Logger log = LoggerFactory.getLogger(PluginBizServiceTemplate.class);



    /*
     * 适合无特殊callback的调用情况
     * @param request 请求参数
     * @param taskTypeEnum 任务类型
     * @return 返回结果
     */
    public <T extends BizServiceBaseRequest, R extends BizServiceBaseResult> R
        execute(T request, BizServiceTypeEnum typeEnum) {
        return execute(request,
                typeEnum,
                call -> PluginHandlerHelper.doHandler(typeEnum, request)
        );
    }

    /*
     * @param request 请求参数
     * @param taskTypeEnum 任务类型
     * @param callback 回调函数
     * @return 返回结果
     */
    public <T extends BizServiceBaseRequest, R extends BizServiceBaseResult> R
        execute(T request, BizServiceTypeEnum typeEnum, ServiceCallback<T, R> callback) {

        log.info("[PluginBizServiceTemplate] execute task type {}，request:{}", typeEnum, request);
        try {
        callback.preHandle();

        if (!callback.checkRequest(request)) {
            log.info("[PluginBizServiceTemplate] check request failed, request:{}, type:{}", request, typeEnum);
            throw new BizServiceException(BizFailCodeEnum.PARAM_FAIL, "参数异常");
        }
            R result = callback.doExecute(request);

            log.info("[PluginBizServiceTemplate] success, result:{}", result);

            return result;

        }catch (BizServiceException e) {
            log.error("[PluginBizServiceTemplate] execute failed, request:{}, type:{}, error:{}", request, typeEnum, e.toString());
            callback.doFail(request, e);
        }
        return null;
    }
}
