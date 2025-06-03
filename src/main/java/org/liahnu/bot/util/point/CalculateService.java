package org.liahnu.bot.util.point;

import org.liahnu.bot.model.type.DirectionType;

import java.math.BigDecimal;
import java.util.Map;

public interface CalculateService {
    /**
     * 计算玩家得分
     *
     * @param context 包含计算所需上下文信息（如番数、庄家、风向等）
     * @return 玩家得分
     */
    Map<DirectionType, BigDecimal> calculate(Map<DirectionType, Integer> context);
}
