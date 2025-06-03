package org.liahnu.bot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.liahnu.bot.model.domain.ContestEnd;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author li hanyu
* @description 针对表【contest_end(比赛结算)】的数据库操作Mapper
* @createDate 2025-06-03 22:03:51
* @Entity org.liahnu.bot.model.domain.ContestEnd
*/
@Mapper
public interface ContestEndMapper extends BaseMapper<ContestEnd> {

}




