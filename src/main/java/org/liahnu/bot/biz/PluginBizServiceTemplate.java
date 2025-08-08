package org.liahnu.bot.biz;

import org.liahnu.bot.biz.base.BizServiceBaseRequest;
import org.liahnu.bot.biz.base.BizServiceBaseResult;
import org.liahnu.bot.biz.base.BizServiceTypeEnum;
import org.liahnu.bot.biz.base.ServiceCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class PluginBizServiceTemplate {

    Logger log = LoggerFactory.getLogger(PluginBizServiceTemplate.class);


    /*
     * @param request 请求参数
     * @param taskTypeEnum 任务类型
     * @param callback 回调函数
     * @return 返回结果
     */
    public <T extends BizServiceBaseRequest, R extends BizServiceBaseResult> void
    execute(BizServiceTypeEnum typeEnum, ServiceCallback<T, R> callback) {
        T request = null;
        try {
            request = callback.buildRequest();

            log.info("[PluginBizServiceTemplate] execute task type {}，request:{}", typeEnum, request);

            if (!callback.preHandle(request)) {
            log.info("[PluginBizServiceTemplate] check request failed, request:{}, type:{}", request, typeEnum);
            throw new BizServiceException(BizFailCodeEnum.PARAM_FAIL, "参数异常");
        }
            // 执行
            R result = callback.doExecute(BizServiceHandlerFactory.getHandler(typeEnum), request);

            log.info("[PluginBizServiceTemplate] success, result:{}", result);
            // 成功处理
            callback.success(result);

        }catch (BizServiceException e) {
            log.error("[PluginBizServiceTemplate] execute failed, request:{}, type:{}, error:{}", request, typeEnum, e.toString());
            callback.fail(request, e);
        }
    }
}
