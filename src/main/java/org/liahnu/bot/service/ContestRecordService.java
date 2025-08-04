package org.liahnu.bot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liahnu.bot.model.domain.ContestRecord;
import org.liahnu.bot.model.type.DirectionType;
import org.liahnu.bot.model.vo.UserRecordVO;

import java.util.List;

/**
* @author li hanyu
* @description 针对表【contest_record(比赛记录)】的数据库操作Service
* @createDate 2025-06-03 16:29:58
*/
public interface ContestRecordService extends IService<ContestRecord> {



    void calculateScore(Integer contestId);

    List<UserRecordVO> getRecentRecord(Long userId, int i);

    ContestRecord queryRecordByCUD(Integer contestId, Integer userId, DirectionType direction);

}
