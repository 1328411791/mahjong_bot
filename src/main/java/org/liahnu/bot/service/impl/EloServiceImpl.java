package org.liahnu.bot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.model.domain.Elo;
import org.liahnu.bot.service.EloService;
import org.liahnu.bot.mapper.EloMapper;
import org.springframework.stereotype.Service;

/**
* @author li hanyu
* @description 针对表【elo(elo表)】的数据库操作Service实现
* @createDate 2025-06-03 23:37:22
*/
@Service
public class EloServiceImpl extends ServiceImpl<EloMapper, Elo>
    implements EloService{

}




