package org.liahnu.bot.service;

import org.liahnu.bot.model.domain.ContestEnd;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liahnu.bot.model.domain.ContestRecord;

import java.util.List;

/**
* @author li hanyu
* @description 针对表【contest_end(比赛结算)】的数据库操作Service
* @createDate 2025-06-03 22:03:51
*/
public interface ContestEndService extends IService<ContestEnd> {

    void calculateScore(Integer contestId, List<ContestRecord> recordList);
}
