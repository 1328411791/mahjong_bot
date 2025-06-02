package org.liahnu.bot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.model.domain.Contest;
import org.liahnu.bot.service.ContestService;
import org.liahnu.bot.mapper.ContestMapper;
import org.springframework.stereotype.Service;

/**
* @author li hanyu
* @description 针对表【contest(比赛)】的数据库操作Service实现
* @createDate 2025-06-03 04:07:19
*/
@Service
public class ContestServiceImpl extends ServiceImpl<ContestMapper, Contest>
    implements ContestService{

}




