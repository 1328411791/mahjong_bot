package org.liahnu.bot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.mapper.ContestRecordMapper;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.domain.ContestRecord;
import org.liahnu.bot.model.type.DirectionType;
import org.liahnu.bot.model.vo.UserRecordVO;
import org.liahnu.bot.service.ContestEndService;
import org.liahnu.bot.service.ContestRecordService;
import org.liahnu.bot.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        return recordCount >= contest.getType().getPlayNum();
    }

    @Override
    public List<UserRecordVO> getRecentRecord(Long userId, int i) {
        return mapper.getUserRecord(userId, i);
    }

    @Override
    public ContestRecord queryRecordByCUD(Integer contestId, Integer userId, DirectionType direction) {
        LambdaQueryWrapper<ContestRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ContestRecord::getContestId, contestId);
        wrapper.eq(ContestRecord::getRecordUserId, userId);
        wrapper.eq(ContestRecord::getDirection, direction);
        return this.getOne(wrapper);
    }
}




