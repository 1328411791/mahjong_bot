package org.liahnu.bot.util.elo;

import lombok.Data;
import org.liahnu.bot.model.type.DirectionType;

import java.math.BigDecimal;
import java.util.Map;

/*
  * 计算上下文
  * @author liahnu
 */
@Data
public class EloCalculateContext {

    // 原始elo积分
    private Map<Long, BigDecimal> originalElo;

    // 本场精算点
    private Map<Long, BigDecimal> scores;






}
