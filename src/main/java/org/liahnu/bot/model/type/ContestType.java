package org.liahnu.bot.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.liahnu.bot.util.elo.EloCalculateService;
import org.liahnu.bot.util.elo.impl.RCREloCalculateServiceImpl;
import org.liahnu.bot.util.point.CalculateService;
import org.liahnu.bot.util.point.impl.BaseRCRCalculateRule;
import org.liahnu.bot.util.point.impl.EmptyCalculateServiceImpl;
import org.liahnu.bot.util.point.impl.MleagueRuleCalculateServiceImpl;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ContestType {

    RCR("RCR", null ,"立直麻将比赛（普通）-四人",4, BaseRCRCalculateRule.class, RCREloCalculateServiceImpl.class),

    MCR("MCR", null,"国标麻将比赛",4, EmptyCalculateServiceImpl.class,RCREloCalculateServiceImpl.class),

    A("RCR_A_RULE", RCR,"A规则比赛",4,null,null),

    M("RCR_M_RULE", RCR,"M规则比赛",4, MleagueRuleCalculateServiceImpl.class,null)
    ;

    // 类型
    private final String type;

    // 父枚举类
    private final ContestType parent;

    // 描述
    private final String description;

    // 游玩人数
    private final Integer playNum;

    // 计算服务类
    private final Class<? extends CalculateService> calculateServiceClass;

    // elo计算类
    private final Class<? extends EloCalculateService> eloCalculateServiceClass;

    public ContestType getParent(){
        return Objects.requireNonNullElse(parent, this);
    }
}
