package org.liahnu.bot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.domain.ContestEnd;
import org.liahnu.bot.model.domain.ContestRecord;
import org.liahnu.bot.model.type.DirectionType;
import org.liahnu.bot.service.ContestEndService;
import org.liahnu.bot.mapper.ContestEndMapper;
import org.liahnu.bot.service.ContestService;
import org.liahnu.bot.util.point.RuleCalculate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Override
    @Transactional
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
    }
}




