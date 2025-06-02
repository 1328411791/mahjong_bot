package org.liahnu.bot.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContestType {

    RCR("RCR", null ,"立直麻将比赛-四人"),

    MCR("MCR", null,"国标麻将比赛"),

    RCR_A_RULE("RCR_A_RULE", RCR,"A规则比赛")
    ;

    // 类型
    private final String type;

    // 父枚举类
    private final ContestType parent;

    // 描述
    private final String description;

}
