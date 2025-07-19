package org.liahnu.bot.biz.base;

import org.liahnu.bot.service.ContestEndService;
import org.liahnu.bot.service.ContestRecordService;
import org.liahnu.bot.service.ContestService;
import org.liahnu.bot.service.EloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

@Component
public abstract class AbstractBizServiceHandler<T extends BizServiceBaseRequest, R extends  BizServiceBaseResult> {


    @Autowired
    protected EloService eloService;

    @Autowired
    protected ContestService contestService;

    @Autowired
    protected ContestRecordService contestRecordService;

    @Autowired
    protected ContestEndService contestEndService;

    @Autowired
    protected TransactionTemplate transactionTemplate;


    public abstract R handle(T request);
}
