package org.liahnu.bot.biz.result.contest;

import lombok.Data;
import org.liahnu.bot.biz.base.BizServiceBaseResult;
import org.liahnu.bot.model.domain.Contest;

@Data
public class CreateContestBizServiceResult extends BizServiceBaseResult {

    /*
      * 比赛信息
     */
    private Contest contest;
}
