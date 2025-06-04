package org.liahnu.bot.biz.handler.contest;

import org.liahnu.bot.biz.BizServiceHandleInterface;
import org.liahnu.bot.biz.base.AbstractBizServiceHandler;
import org.liahnu.bot.biz.base.BizServiceTypeEnum;
import org.liahnu.bot.biz.request.contest.CreateContestBizServiceRequest;
import org.liahnu.bot.biz.result.contest.CreateContestBizServiceResult;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.type.ContestStatus;

@BizServiceHandleInterface(type = BizServiceTypeEnum.CREATE_CONTEST)
public class CreateContestBizHandler
        extends AbstractBizServiceHandler<CreateContestBizServiceRequest, CreateContestBizServiceResult> {

    @Override
    public CreateContestBizServiceResult handle(CreateContestBizServiceRequest request) {
        Contest contest = new Contest();
        contest.setCreateUserId(request.getUserId());
        contest.setCreateGroupId(request.getGroupId());
        contest.setType(request.getContestType());
        contest.setStatus(ContestStatus.NOT_START);
        super.contestService.save(contest);

        CreateContestBizServiceResult result = new CreateContestBizServiceResult();
        result.setContest(contest);

        return result;
    }
}
