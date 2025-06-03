package org.liahnu.bot.util.elo;

import org.liahnu.bot.model.type.DirectionType;

import java.math.BigDecimal;
import java.util.Map;

public interface EloService {
    /**
     * 计算玩家得分
     *
     * @param context 包含计算所需上下文信息，context.originalElo 为玩家原始得分，context.scores 为玩家得分
     * @return 玩家得分
     */
    Map<Long, BigDecimal> calculate(EloCalculateContext context);
}
