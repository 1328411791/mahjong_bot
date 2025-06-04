package org.liahnu.bot.biz.result.record;

import lombok.Data;
import org.liahnu.bot.biz.base.BizServiceBaseResult;
import org.liahnu.bot.model.domain.ContestRecord;

@Data
public class AddContestRecordBizServiceResult extends BizServiceBaseResult {

    private ContestRecord record;
}
