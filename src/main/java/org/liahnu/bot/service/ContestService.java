package org.liahnu.bot.service;

import org.liahnu.bot.model.domain.Contest;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liahnu.bot.model.type.ContestType;

import java.util.List;

/**
* @author li hanyu
* @description 针对表【contest(比赛)】的数据库操作Service
* @createDate 2025-06-03 04:21:35
*/
public interface ContestService extends IService<Contest> {

    Contest createContest(Long userId, Long groupId, ContestType type);

    List<Contest> queryLastContest4Group(Long groupId, Integer page, Integer size);
}
