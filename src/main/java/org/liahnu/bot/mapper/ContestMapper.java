package org.liahnu.bot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.liahnu.bot.model.domain.Contest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author li hanyu
* @description 针对表【contest(比赛)】的数据库操作Mapper
* @createDate 2025-06-03 04:21:35
* @Entity org.liahnu.bot.model.domain.Contest
*/
@Mapper
public interface ContestMapper extends BaseMapper<Contest> {

}




