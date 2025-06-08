package org.liahnu.bot.util.elo;


import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.liahnu.bot.model.type.ContestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;


public class CalculateEloTest {

    private final static Logger logger = LoggerFactory.getLogger(CalculateEloTest.class);

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


        logger.info(calculate.toString());

        Map<Long, BigDecimal> ret = Map.of(
                1L, new BigDecimal("1030.398"),
                2L, new BigDecimal("1007.742"),
                3L, new BigDecimal("989.91"),
                4L, new BigDecimal("972.064")
        );

        Assert.equals(calculate, ret);

    }

}
