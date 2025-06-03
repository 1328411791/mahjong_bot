package org.liahnu.bot.util.point;


import cn.hutool.core.lang.Assert;
import org.junit.jupiter.api.Test;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.model.type.DirectionType;

import java.math.BigDecimal;
import java.util.Map;

public class CalculatePointTest {

    @Test
    public void test4BaseRule() {

        Map<DirectionType, Integer> context = Map.of(
                DirectionType.EAST, 26000,
                DirectionType.SOUTH, 26000,
                DirectionType.WEST, 24000,
                DirectionType.NORTH, 24000
        );

        Map<DirectionType, BigDecimal> rmu = RuleCalculate.calculate(ContestType.RCR, context);

        System.out.println(rmu);

        Map<DirectionType, BigDecimal> complete = Map.of(
                DirectionType.EAST, new BigDecimal("39.4"),
                DirectionType.SOUTH, new BigDecimal("12.9"),
                DirectionType.WEST, new BigDecimal("-18.9"),
                DirectionType.NORTH, new BigDecimal("-33.4")
        );

        Assert.equals(complete, rmu);

    }

}
