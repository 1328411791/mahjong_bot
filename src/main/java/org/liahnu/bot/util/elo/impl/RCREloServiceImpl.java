package org.liahnu.bot.util.elo.impl;

import org.liahnu.bot.model.type.DirectionType;
import org.liahnu.bot.util.elo.EloCalculateContext;
import org.liahnu.bot.util.elo.EloService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RCREloServiceImpl implements EloService {
    // 基础K系数，适用于立直麻将的经验值
    private static final int BASE_K_FACTOR = 400;

    @Override
    public Map<Long, BigDecimal> calculate(EloCalculateContext context) {
        Map<Long, BigDecimal> ratings = context.getOriginalElo();
        Map<Long, BigDecimal> scores = context.getScores();

        if (ratings == null || scores == null || ratings.size() != 4 || scores.size() != 4) {
            throw new IllegalArgumentException("需要四个玩家的有效数据");
        }

        Map<Long, BigDecimal> eloChanges = new HashMap<>();

        List<Long> playerIds = new ArrayList<>(ratings.keySet());

        for (int i = 0; i < playerIds.size(); i++) {
            Long playerId = playerIds.get(i);
            long playerRating = ratings.get(playerId).longValue();
            int playerScore = scores.get(playerId).intValue();


            double newRating = 0;
            // 与其他三个玩家比较
            for (int j = 0; j < playerIds.size(); j++) {
                if (i == j) continue;
                Long opponentId = playerIds.get(j);
                double opponentRating = ratings.get(opponentId).doubleValue();
                double opponentScore = scores.get(opponentId).doubleValue();

                double expected = calculateExpectedScore(playerRating, opponentRating);
                double actual = calculateActualScore(playerScore, opponentScore);
                double kFactor = calculateKFactor(playerScore, opponentScore);

                newRating += updateRating(playerRating, kFactor, actual, expected);
            }
            BigDecimal change = BigDecimal.valueOf(newRating - playerRating);
            eloChanges.put(playerId, change);
        }

        return eloChanges;
    }

    /**
     * 计算期望得分 We
     */
    private double calculateExpectedScore(double playerRating, double opponentRating) {
        return 1.0 / (1 + Math.pow(10, (opponentRating - playerRating) / 400.0));
    }

    /**
     * 计算实际得分 Sa
     */
    private double calculateActualScore(double playerScore, double opponentScore) {

//        if (playerScore > opponentScore) return 1.0;
//        if (playerScore == opponentScore) return 0.5;
        return playerScore;
    }

    /**
     * 动态计算K系数（基于分差）
     */
    private double calculateKFactor(double playerScore, double opponentScore) {
        double ptDiff = Math.abs(playerScore - opponentScore);
        return  16 * (1 + ptDiff / 400);
    }

    /**
     * 更新Elo评分
     */
    private long updateRating(double currentRating, double kFactor, double actualScore, double expectedScore) {
        return Math.round(currentRating + kFactor * (actualScore - expectedScore));
    }
}
