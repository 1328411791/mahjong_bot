package org.liahnu.bot.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContestStatus {

    NOT_START("未开始", "比赛未开始"),

    START("进行中", "比赛正在进行中"),

    END("已结束", "比赛已结束"),
    ;



    private final String status;

    private final String description;
}
