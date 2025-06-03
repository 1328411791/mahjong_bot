package org.liahnu.bot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.liahnu.bot.model.domain.Elo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author li hanyu
* @description 针对表【elo(elo表)】的数据库操作Mapper
* @createDate 2025-06-03 23:37:22
* @Entity org.liahnu.bot.model.domain.Elo
*/
@Mapper
public interface EloMapper extends BaseMapper<Elo> {

}




