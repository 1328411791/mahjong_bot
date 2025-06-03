package org.liahnu.bot.util.point;

import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.model.type.DirectionType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;


public class RuleCalculate {
    static final Map<String, CalculateService> RULE_MAP = new HashMap<>();

    static {
        ServiceLoader<CalculateService> loader = ServiceLoader.load(CalculateService.class);
        for (CalculateService calculator : loader) {
            String name = calculator.getClass().getName();
            RULE_MAP.put(name, calculator);
        }
    }

    public static Map<DirectionType, BigDecimal> calculate(ContestType type, Map<DirectionType, Integer> context) {

        Class<? extends CalculateService> clazz = type.getCalculateServiceClass();
        if(clazz ==null){
            clazz = type.getParent().getCalculateServiceClass();
        }

        CalculateService calculator = RULE_MAP.get(clazz.getName());
        if (calculator == null) {
            throw new IllegalArgumentException("不支持的规则: " + clazz.getName());
        }
        return calculator.calculate(context);
    }

}
