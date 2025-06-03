package org.liahnu.bot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.model.domain.ContestRecord;
import org.liahnu.bot.service.ContestRecordService;
import org.liahnu.bot.mapper.ContestRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author li hanyu
* @description 针对表【contest_record(比赛记录)】的数据库操作Service实现
* @createDate 2025-06-03 16:29:58
*/
@Service
public class ContestRecordServiceImpl extends ServiceImpl<ContestRecordMapper, ContestRecord>
    implements ContestRecordService{

}




