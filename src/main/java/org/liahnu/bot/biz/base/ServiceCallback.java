package org.liahnu.bot.biz.base;

public interface ServiceCallback
        <T extends BizServiceBaseRequest, R extends BizServiceBaseResult> {

    default void preHandle() {

    }

    R doExecute(T request);


}
