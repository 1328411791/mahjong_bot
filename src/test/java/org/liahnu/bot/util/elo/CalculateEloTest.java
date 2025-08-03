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
        Map<Integer, BigDecimal> originalElo = Map.of(
                1, new BigDecimal("1000"),
                2, new BigDecimal("1000"),
                3, new BigDecimal("1000"),
                4, new BigDecimal("1000")
        );
        Map<Integer, BigDecimal> scores = Map.of(
                1, new BigDecimal("80"),
                2, new BigDecimal("-3.8"),
                3, new BigDecimal("-26.4"),
                4, new BigDecimal("-49.7")
        );

        context.setOriginalElo(originalElo);
        context.setScores(scores);

        Map<Integer, BigDecimal> calculate = EloCalculate.calculate(ContestType.RCR, context);


        logger.info(calculate.toString());

        Map<Integer, BigDecimal> ret = Map.of(
                1, new BigDecimal("1030.398"),
                2, new BigDecimal("1007.742"),
                3, new BigDecimal("989.91"),
                4, new BigDecimal("972.064")
        );

        Assert.equals(calculate, ret);

    }

}
