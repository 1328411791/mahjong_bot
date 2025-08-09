package org.liahnu.bot.biz.result.contest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.liahnu.bot.biz.base.BizServiceBaseResult;
import org.liahnu.bot.model.domain.Elo;
import org.liahnu.bot.model.vo.contest.UserLastContestVO;

import java.util.List;

/**
 * @author lihanyu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryUserContestDetailResult extends BizServiceBaseResult {

    /*
     *  最近比赛信息
     */
    private List<UserLastContestVO> userLastContest;


    /*
     * 用户最新的elo
     */
    private Elo lastContestElo;
}
