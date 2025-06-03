package org.liahnu.bot.util.elo;


import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.model.type.DirectionType;
import org.liahnu.bot.util.point.RuleCalculate;

import java.math.BigDecimal;
import java.util.Map;

public class CalculateEloTest {

    @Test
    public void test4BaseRule() {

        EloCalculateContext context = new EloCalculateContext();
        Map<Long,BigDecimal> originalElo = Map.of(
                1L, new BigDecimal("1000"),
                2L, new BigDecimal("1000"),
                3L, new BigDecimal("1000"),
                4L, new BigDecimal("1000")
        );
        Map<Long,BigDecimal> scores = Map.of(
                1L, new BigDecimal("80"),
                2L, new BigDecimal("-3.8"),
                3L, new BigDecimal("-26.4"),
                4L, new BigDecimal("-49.7")
        );

        context.setOriginalElo(originalElo);
        context.setScores(scores);

        Map<Long, BigDecimal> calculate = EloCalculate.calculate(ContestType.RCR, context);


        System.out.println(calculate);

    }

}
