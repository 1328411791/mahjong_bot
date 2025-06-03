package org.liahnu.bot.service;

import org.liahnu.bot.model.domain.ContestRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
* @author li hanyu
* @description 针对表【contest_record(比赛记录)】的数据库操作Service
* @createDate 2025-06-03 16:29:58
*/
public interface ContestRecordService extends IService<ContestRecord> {



    @Transactional
    boolean addRecord(Integer contestId, String direction, Integer score, Long recordUserId, Long groupId);
}
