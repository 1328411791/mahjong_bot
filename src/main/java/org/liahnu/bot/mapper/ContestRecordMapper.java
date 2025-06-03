package org.liahnu.bot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.liahnu.bot.model.domain.ContestRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author li hanyu
* @description 针对表【contest_record(比赛记录)】的数据库操作Mapper
* @createDate 2025-06-03 16:29:58
* @Entity org.liahnu.bot.model.domain.ContestRecord
*/
@Mapper
public interface ContestRecordMapper extends BaseMapper<ContestRecord> {

}




