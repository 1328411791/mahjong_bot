package org.liahnu.bot.biz.handler.contest;

import org.liahnu.bot.biz.BizServiceHandleInterface;
import org.liahnu.bot.biz.base.AbstractBizServiceHandler;
import org.liahnu.bot.biz.base.BizServiceTypeEnum;
import org.liahnu.bot.biz.request.contest.QueryUserContestDetailRequest;
import org.liahnu.bot.biz.result.contest.QueryUserContestDetailResult;
import org.liahnu.bot.model.domain.Elo;
import org.liahnu.bot.model.domain.User;
import org.liahnu.bot.model.vo.contest.UserLastContestVO;

import java.util.List;

/**
 * @author lihanyu
 */
@BizServiceHandleInterface(type = BizServiceTypeEnum.QUERY_USER_CONTEST_DETAIL)
public class QueryUserContestDetailHandler
        extends AbstractBizServiceHandler<QueryUserContestDetailRequest, QueryUserContestDetailResult> {

    @Override
    public QueryUserContestDetailResult handle(QueryUserContestDetailRequest request) {

        QueryUserContestDetailResult result = new QueryUserContestDetailResult();

        User user = userService.queryByQQId(request.getUserQqId());

        List<UserLastContestVO> contests = contestService.queryUserLastContestDetail(user.getId(), request.getContestType(), request.getLimit());

        result.setUserLastContest(contests);

        Elo elo = eloService.queryUserEloByType(user.getId(), request.getContestType());
        result.setLastContestElo(elo);

        return result;
    }
}
