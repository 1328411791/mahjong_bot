package org.liahnu.bot.biz;

import org.liahnu.bot.biz.base.BizServiceBaseRequest;
import org.liahnu.bot.biz.base.BizServiceBaseResult;
import org.liahnu.bot.biz.base.BizServiceTypeEnum;
import org.liahnu.bot.biz.base.ServiceCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class BizServiceTemplate {

    Logger log = LoggerFactory.getLogger(BizServiceTemplate.class);



    /*
     * @param request 请求参数
     * @param taskTypeEnum 任务类型
     * @return 返回结果
     */
    public <T extends BizServiceBaseRequest, R extends BizServiceBaseResult> R
        execute(T request, BizServiceTypeEnum typeEnum) {
        return execute(request,
                typeEnum,
                call ->  (R) BizServiceHandlerFactory.getHandler(typeEnum).handle(request)
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

        log.info("[BizServiceTemplate] execute task type {}，request:{}", typeEnum, request);

        callback.preHandle();

        if (!callback.checkRequest(request)) {
            log.info("[BizServiceTemplate] check request failed, request:{}, type:{}", request, typeEnum);
            throw new BizServiceException(BizFailCodeEnum.PARAM_FAIL, "参数异常");
        }

        R result = callback.doExecute(request);

        log.info("[BizServiceTemplate] success, result:{}", result);

        return result;
    }


}
