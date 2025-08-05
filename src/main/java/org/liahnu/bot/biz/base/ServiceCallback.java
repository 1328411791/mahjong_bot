package org.liahnu.bot.biz.base;

import lombok.extern.slf4j.Slf4j;
import org.liahnu.bot.biz.BizServiceException;

@Slf4j
public interface ServiceCallback
        <T extends BizServiceBaseRequest, R extends BizServiceBaseResult> {

    /*
     * 预处理
     */
    default void preHandle() {

    }

    /*
     * 执行
     */
    R doExecute(T request);

    /*
     * 校验请求参数
     * @param request 请求参数
     * @return 是否校验通过 true:通过 false:不通过
     */
    default boolean checkRequest(T request) {
       return true;
    }

    default void doFail(T request, BizServiceException e) {

    }
}
