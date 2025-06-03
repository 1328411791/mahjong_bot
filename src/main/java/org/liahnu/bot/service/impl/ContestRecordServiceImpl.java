package org.liahnu.bot.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.domain.ContestRecord;
import org.liahnu.bot.model.type.ContestStatus;
import org.liahnu.bot.model.type.DirectionType;
import org.liahnu.bot.service.ContestRecordService;
import org.liahnu.bot.mapper.ContestRecordMapper;
import org.liahnu.bot.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author li hanyu
* @description 针对表【contest_record(比赛记录)】的数据库操作Service实现
* @createDate 2025-06-03 16:29:58
*/
@Service
public class ContestRecordServiceImpl extends ServiceImpl<ContestRecordMapper, ContestRecord>
    implements ContestRecordService{

    @Autowired
    private ContestRecordMapper mapper;

    @Autowired
    private ContestService contestService;


    @Override
    @Transactional
    public boolean addRecord(Integer contestId, String direction, Integer score, Long recordUserId, Long groupId) {

        DirectionType directionType = DirectionType.getDirectionType(direction);

        Contest contest = contestService.getById(contestId);
        if (contest == null){
            log.error("比赛不存在");
            return false;
        }
        // 检查是否是创建者所在的比赛
        Assert.equals(contest.getCreateGroupId(),groupId);

        ContestRecord record = new ContestRecord();
        record.setContestId(contestId);
        record.setDirection(directionType);
        record.setPoint(score);
        record.setRecordUserId(recordUserId);
        this.save(record);

        // 添加完成后，检查是否达到阈值
        boolean flag = this.checkThreshold(contestId, contest);
        if (flag){
            // 阈值达到，更新比赛状态为结束
            contest.setStatus(ContestStatus.END);
            contestService.updateById(contest);
            // TODO: 计算分数
        }
        return true;
    }

    private boolean checkThreshold(Integer contestId,Contest contest) {
        // 获取当前记录的记录数量
        Integer recordCount = mapper.getContestRecordCount(contestId);

        if (recordCount >= contest.getType().getPlayNum()){
            return true;
        }
        return false;
    }
}




