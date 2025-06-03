package org.liahnu.bot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.liahnu.bot.model.domain.Elo;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.service.EloService;
import org.liahnu.bot.mapper.EloMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* @author li hanyu
* @description 针对表【elo(elo表)】的数据库操作Service实现
* @createDate 2025-06-03 23:37:22
*/
@Service
public class EloServiceImpl extends ServiceImpl<EloMapper, Elo>
    implements EloService{

    private Elo createDefaultElo(Long userId,ContestType type) {
        Elo elo = new Elo();
        elo.setUserId(userId);
        elo.setElo(new BigDecimal(2000));
        elo.setType(type.getParent());
        this.save(elo);
        return elo;
    }

    @Override
    public BigDecimal getElo(Long userId, ContestType type) {

        Elo elo = this.getOne(new QueryWrapper<Elo>().eq("user_id", userId));
        if(elo == null){
            elo = createDefaultElo(userId, type);
        }
        return elo.getElo();
    }

    @Override
    public List<Elo> updateElo(Map<Long, BigDecimal> map, ContestType type){
        List<Elo> ret = new  ArrayList<>();

        for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
            Elo elo = this.getOne(new QueryWrapper<Elo>().eq("user_id", entry.getKey()));
            elo.setElo(entry.getValue());
            this.updateById(elo);

            ret.add(elo);
        }
        return ret;
    }

}




