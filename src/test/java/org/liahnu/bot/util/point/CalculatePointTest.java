package org.liahnu.bot.util.point;


import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.model.type.DirectionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Map;

public class CalculatePointTest {

    private final static Logger logger = LoggerFactory.getLogger(CalculatePointTest.class);

    @Test
    public void test4BaseRule() {

        Map<DirectionType, Integer> context = Map.of(
                DirectionType.EAST, 26000,
                DirectionType.SOUTH, 26000,
                DirectionType.WEST, 24000,
                DirectionType.NORTH, 24000
        );

        Map<DirectionType, BigDecimal> rmu = RuleCalculate.calculate(ContestType.RCR, context);

        logger.info(rmu.toString());

        Map<DirectionType, BigDecimal> complete = Map.of(
                DirectionType.EAST, new BigDecimal("16"),
                DirectionType.SOUTH, new BigDecimal("-6"),
                DirectionType.WEST, new BigDecimal("-16"),
                DirectionType.NORTH, new BigDecimal("6")
        );

        Assert.equals(complete, rmu);

    }

}
