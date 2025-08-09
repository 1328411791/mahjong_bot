package org.liahnu.bot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.type.ContestStatus;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.model.vo.contest.UserLastContestVO;

import java.util.List;

/**
* @author li hanyu
* @description 针对表【contest(比赛)】的数据库操作Service
* @createDate 2025-06-03 04:21:35
*/
public interface ContestService extends IService<Contest> {

    Contest createContest(Integer userId, Long groupId, ContestType type);

    List<Contest> queryLastContest4Group(Long groupId, Integer page, Integer size);

    boolean updateContestStatus(Integer contestId, ContestStatus contestStatus);

    /**
     * 查询用户最近的比赛
     *
     * @param id          用户 id
     * @param contestType 比赛类型
     * @param limit       限制数量
     * @return 用户最近的比赛
     */
    List<UserLastContestVO> queryUserLastContestDetail(Integer id, ContestType contestType, Integer limit);
}
