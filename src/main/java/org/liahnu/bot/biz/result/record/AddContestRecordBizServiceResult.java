package org.liahnu.bot.biz.result.record;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.liahnu.bot.biz.base.BizServiceBaseResult;
import org.liahnu.bot.model.domain.ContestRecord;

@EqualsAndHashCode(callSuper = true)
@Data
public class AddContestRecordBizServiceResult extends BizServiceBaseResult {

    private ContestRecord record;
}
