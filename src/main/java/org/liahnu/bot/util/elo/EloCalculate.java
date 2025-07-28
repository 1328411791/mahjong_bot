package org.liahnu.bot.util.elo;

import org.liahnu.bot.model.type.ContestType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;


public class EloCalculate {
    static final Map<String, EloCalculateService> RULE_MAP = new HashMap<>();

    static {
        ServiceLoader<EloCalculateService> loader = ServiceLoader.load(EloCalculateService.class);
        for (EloCalculateService calculator : loader) {
            String name = calculator.getClass().getName();
            RULE_MAP.put(name, calculator);
        }
    }

    public static Map<Integer, BigDecimal> calculate(ContestType type, EloCalculateContext context) {

        Class<? extends EloCalculateService> clazz = type.getEloCalculateServiceClass();
        if(clazz ==null && type.getParent()!=null){
            clazz = type.getParent().getEloCalculateServiceClass();
        }

        EloCalculateService calculator = RULE_MAP.get(clazz.getName());
        if (calculator == null) {
            throw new IllegalArgumentException("不支持的规则: " + clazz.getName());
        }
        return calculator.calculate(context);
    }

}
