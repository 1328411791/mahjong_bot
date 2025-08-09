package org.liahnu.bot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.model.vo.contest.UserLastContestVO;

import java.util.List;

/**
* @author li hanyu
* @description 针对表【contest(比赛)】的数据库操作Mapper
* @createDate 2025-06-03 04:21:35
* @Entity org.liahnu.bot.model.domain.Contest
*/
@Mapper
public interface ContestMapper extends BaseMapper<Contest> {

    /*
     * 统计用户最近 limit 场比赛记录结果
     */
    List<UserLastContestVO> selectRecentContest(Integer userId, ContestType type, Integer limit);

}




