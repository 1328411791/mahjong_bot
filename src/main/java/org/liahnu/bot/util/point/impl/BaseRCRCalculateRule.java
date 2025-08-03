package org.liahnu.bot.util.point.impl;

import org.liahnu.bot.model.type.DirectionType;
import org.liahnu.bot.util.point.CalculateService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class BaseRCRCalculateRule implements CalculateService {

    /*
      * 默认的RCR规则，计算最终pt，雀魂规
      * 马点 +15 +5 -5 -15
      * 一位返点 30000
     */
    @Override
    public Map<DirectionType, BigDecimal> calculate(Map<DirectionType, Integer> context) {
        if (context == null || context.isEmpty()) {
            return new HashMap<>();
        }

        // 检查是否包含所有四个方向
        Set<DirectionType> directions = EnumSet.allOf(DirectionType.class);
        if (!context.keySet().containsAll(directions)) {
            throw new IllegalArgumentException("必须包含所有四个方向的得分信息");
        }

        // 计算总分数是否为100000
        int totalScore = context.values().stream().mapToInt(Integer::intValue).sum();
        if (totalScore != 100000) {
            throw new IllegalArgumentException("总分数必须为100000");
        }

        // 按分数从高到低排序
        List<Map.Entry<DirectionType, Integer>> sortedEntries = new ArrayList<>(context.entrySet());
        sortedEntries.sort(
                (e1, e2) -> {
                    int scoreComparison = e2.getValue().compareTo(e1.getValue());

                    // 如果分数相同，按照DirectionType的code升序排列（1:东, 2:南, 3:西, 4:北）
                    if (scoreComparison == 0) {
                        return Integer.compare(e1.getKey().getDirection(), e2.getKey().getDirection());
                    }

                    return scoreComparison;
                });

        // 计算马点
        int[] maPoints = {15, 5, -5, -15};

        // 创建结果映射
        Map<DirectionType, BigDecimal> result = new HashMap<>();

        // 应用排名和马点
        for (int i = 0; i < sortedEntries.size(); i++) {
            Map.Entry<DirectionType, Integer> entry = sortedEntries.get(i);
            DirectionType direction = entry.getKey();
            Integer score = entry.getValue();

            // 计算PT值
            BigDecimal ptValue = new BigDecimal(score - 25000).divide(new BigDecimal(1000), 2, RoundingMode.HALF_UP);

            BigDecimal finalScore = ptValue.add(BigDecimal.valueOf(maPoints[i]));

            result.put(direction, finalScore);
        }

        return result;
    }
}
