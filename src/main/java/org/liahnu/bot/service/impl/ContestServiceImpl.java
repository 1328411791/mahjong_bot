package org.liahnu.bot.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.mapper.ContestMapper;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.type.ContestStatus;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.service.ContestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author li hanyu
* @description 针对表【contest(比赛)】的数据库操作Service实现
* @createDate 2025-06-03 04:21:35
*/
@Service
public class ContestServiceImpl extends ServiceImpl<ContestMapper, Contest>
    implements ContestService{

    @Override
    public Contest createContest(Integer userId, Long groupId, ContestType type) {
        Contest contest = new Contest();
        contest.setCreateUserId(userId);
        contest.setCreateGroupId(groupId);
        contest.setType(type);
        contest.setStatus(ContestStatus.NOT_START);
        this.save(contest);

        // 获取插入的id
        return contest;
    }

    @Override
    public List<Contest> queryLastContest4Group(Long groupId, Integer page, Integer size) {
        Page<Contest> contestPage = new Page<>(page,size);
        LambdaQueryWrapper<Contest> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Contest::getCreateGroupId,groupId);
        queryWrapper.ne(Contest::getStatus, ContestStatus.END);
        queryWrapper.orderByDesc(Contest::getCreateTime);
        return this.page(contestPage,queryWrapper).getRecords();
    }


    @Transactional
    @Override
    public boolean updateContestStatus(Integer contestId, ContestStatus contestStatus) {
        Contest contest = this.getById(contestId);
        Assert.isNull(contest, "比赛不存在");
        contest.setStatus(contestStatus);
        return this.updateById(contest);
    }
}




