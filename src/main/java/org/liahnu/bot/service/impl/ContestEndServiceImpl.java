package org.liahnu.bot.service.impl;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikuac.shiro.common.utils.MsgUtils;
import com.mikuac.shiro.core.Bot;
import org.liahnu.bot.mapper.ContestEndMapper;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.domain.ContestEnd;
import org.liahnu.bot.model.domain.ContestRecord;
import org.liahnu.bot.model.domain.Elo;
import org.liahnu.bot.model.type.ContestStatus;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.model.type.DirectionType;
import org.liahnu.bot.service.ContestEndService;
import org.liahnu.bot.service.ContestService;
import org.liahnu.bot.service.EloService;
import org.liahnu.bot.util.SignSendMessageComponent;
import org.liahnu.bot.util.elo.EloCalculate;
import org.liahnu.bot.util.elo.EloCalculateContext;
import org.liahnu.bot.util.point.RuleCalculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
* @author li hanyu
* @description 针对表【contest_end(比赛结算)】的数据库操作Service实现
* @createDate 2025-06-03 22:03:51
*/
@Service
public class ContestEndServiceImpl extends ServiceImpl<ContestEndMapper, ContestEnd>
    implements ContestEndService{

    @Autowired
    private ContestService contestService;

    @Autowired
    private SignSendMessageComponent botContainer;

    @Autowired
    private EloService eloService;

    @Override
    @Transactional
    @Async
    public void calculateScore(Integer contestId, List<ContestRecord> recordList) {
        // 获取当前记录的记录数量
        Contest contest = contestService.getById(contestId);

        // 类型转换
        Map<DirectionType,Integer> context = new HashMap<>(recordList.size());
        recordList.forEach(
                record ->{
                    context.put(record.getDirection(),record.getPoint());
                }
        );

        Map<DirectionType, BigDecimal> calculate = RuleCalculate.calculate(contest.getType(), context);

        calculate.forEach(
                (direction,point) ->{
                    ContestEnd contestEnd = new ContestEnd();
                    contestEnd.setContestId(contestId);
                    for(ContestRecord record: recordList){
                        if(record.getDirection() == direction){
                            contestEnd.setUserId(record.getRecordUserId());
                        }
                    }
                    contestEnd.setEndPoint(point);
                    this.save(contestEnd);
                }
        );

        // 完成精算
        contest.setStatus(ContestStatus.END);
        contestService.updateById(contest);

        Map<Long, Pair<BigDecimal,BigDecimal>> calculateElo = calculateElo(contestId, contest.getType());

        // 更新elo变化记录
        updateChangeElo(calculateElo,contest.getId());


        Bot bot = botContainer.getBot();

        MsgUtils msg = MsgUtils.builder();
        msg.text("🏆 比赛结束！以下是比赛结果：\n");
        msg.text("------------------------------\n");
        msg.text("比赛类型：" + contest.getType() + "\n");
        msg.text("比赛ID：" + contest.getId() + "\n");
        msg.text("------------------------------\n");

        calculateElo.forEach((userId,elo) -> {
            BigDecimal oldElo = elo.getKey(); // 原始 Elo
            BigDecimal newElo = elo.getValue();     // 新 Elo
            BigDecimal change = newElo.subtract(oldElo); // Elo 变化值

            String sign = change.compareTo(BigDecimal.ZERO) >= 0 ? "+" : ""; // 正负号处理

            ContestRecord contestRecord = null;
            for(ContestRecord record: recordList){
                if(Objects.equals(record.getRecordUserId(), userId)){
                    contestRecord = record;
                    break;
                }
            }

            msg.text("👤 用户 ID: ").at(userId).text("\n");
            msg.text("📈 点数情况").text((contestRecord.getPoint().compareTo(0) >= 0 ? "+" : "")+contestRecord.getPoint()).text("\n");
            msg.text("📈 ELO: ").text(oldElo.toString()).text(" → ").text(newElo.toString())
                    .text(" (").text(sign).text(change.toPlainString()).text(")\n");

            msg.text("------------------------------\n");
        });


        // 发送消息到创建比赛的群组
        bot.sendGroupMsg(contest.getCreateGroupId(), msg.build(), false);
    }

    private void updateChangeElo(Map<Long, Pair<BigDecimal, BigDecimal>> calculateElo, Integer contestID) {
        calculateElo.forEach((userId, pair) ->{
            ContestEnd contestEnd = this.query().eq("contest_id",contestID).eq("user_id",userId).one();
            contestEnd.setEloChange(pair.getValue().subtract(pair.getKey()));
            this.updateById(contestEnd);
        });
    }

    /*
    * 计算 Elo
    * @param contestId 比赛 ID
    * @param contestType 比赛类型
    * @return Map<Long, Pair<BigDecimal,BigDecimal>> 用户 ID 与 Elo 变化值的映射
     */
    public Map<Long, Pair<BigDecimal,BigDecimal>> calculateElo(Integer contestId, ContestType contestType) {
        List<ContestEnd> contestEndList = this.list(new QueryWrapper<ContestEnd>().eq("contest_id",contestId));

        EloCalculateContext context = new EloCalculateContext();

        Map<Long,BigDecimal> score = new HashMap<>();
        contestEndList.forEach((contestEnd -> {
            score.put(contestEnd.getUserId(),contestEnd.getEndPoint());
        }));
        context.setScores(score);

        Map<Long,BigDecimal> originalElo = new HashMap<>();
        for(ContestEnd contestEnd:contestEndList){
            BigDecimal elo = eloService.getElo(contestEnd.getUserId(), contestType);
            originalElo.put(contestEnd.getUserId(),elo);
        }

        context.setOriginalElo(originalElo);

        Map<Long,BigDecimal> eloChange = EloCalculate.calculate(contestType, context);

        List<Elo> changeElo = eloService.updateElo(eloChange, contestType);

        // 将originalElo 和 changeElo 合并map输出
        Map<Long, Pair<BigDecimal,BigDecimal>> ret = new HashMap<>();
        for (Elo elo : changeElo){
            ret.put(elo.getUserId(), Pair.of(originalElo.get(elo.getUserId()),elo.getElo()));
        }
        return ret;
    }

}




