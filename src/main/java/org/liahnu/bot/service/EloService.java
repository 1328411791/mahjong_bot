package org.liahnu.bot.service;

import org.liahnu.bot.model.domain.Elo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liahnu.bot.model.type.ContestType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
* @author li hanyu
* @description 针对表【elo(elo表)】的数据库操作Service
* @createDate 2025-06-03 23:37:22
*/
public interface EloService extends IService<Elo> {

    BigDecimal getElo(Long userId, ContestType type);

    List<Elo> updateElo(Map<Long, BigDecimal> map, ContestType type);
}
