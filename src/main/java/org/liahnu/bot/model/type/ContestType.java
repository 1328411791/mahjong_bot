package org.liahnu.bot.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContestType {

    RCR("RCR", null ,"立直麻将比赛-四人",4),

    MCR("MCR", null,"国标麻将比赛",4),

    RCR_A_RULE("RCR_A_RULE", RCR,"A规则比赛",4)
    ;

    // 类型
    private final String type;

    // 父枚举类
    private final ContestType parent;

    // 描述
    private final String description;

    // 游玩人数
    private final Integer playNum;

}
