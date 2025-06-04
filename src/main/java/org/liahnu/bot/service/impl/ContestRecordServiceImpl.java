package org.liahnu.bot.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.domain.ContestRecord;
import org.liahnu.bot.model.type.ContestStatus;
import org.liahnu.bot.model.type.DirectionType;
import org.liahnu.bot.service.ContestEndService;
import org.liahnu.bot.service.ContestRecordService;
import org.liahnu.bot.mapper.ContestRecordMapper;
import org.liahnu.bot.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    private ContestEndService contestEndService;


    @Override
    @Transactional
    public ContestRecord addRecord(Integer contestId, String direction, Integer score, Long recordUserId, Long groupId) {

        DirectionType directionType = DirectionType.getDirectionType(direction);

        Contest contest = contestService.getById(contestId);
        if (contest == null){
            log.error("比赛不存在");
            throw new RuntimeException("比赛不存在");
        }
        // 检查是否是创建者所在的比赛
        Assert.equals(contest.getCreateGroupId(),groupId);

        ContestRecord record = new ContestRecord();
        record.setContestId(contestId);
        record.setDirection(directionType);
        record.setPoint(score);
        record.setRecordUserId(recordUserId);
        this.save(record);

        if(contest.getStatus()== ContestStatus.NOT_START){
            contest.setStatus(ContestStatus.START);
            contestService.updateById(contest);
        }


        this.calculateScore(contestId);

        return record;
    }

    @Override
    public void calculateScore(Integer contestId) {

        boolean b = checkThreshold(contestId);
        if (!b){
            return;
        }
        List<ContestRecord> recordList = this.list(new QueryWrapper<ContestRecord>().eq("contest_id",contestId));
        contestEndService.calculateScore(contestId,recordList);

    }




    private boolean checkThreshold(Integer contestId) {
        // 获取当前记录的记录数量
        Integer recordCount = mapper.getContestRecordCount(contestId);
        Contest contest = contestService.getById(contestId);

        if (contest == null){
            log.error("比赛不存在");
            throw new RuntimeException("比赛不存在");
        }

        if (recordCount >= contest.getType().getPlayNum()){
            return true;
        }

        return false;
    }

    @Override
    public List<ContestRecord> getRecentRecord(Long userId, int i) {
        return null;
    }
}




