package org.liahnu.bot.biz.base;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BizServiceTypeEnum {

    TEST("test", "测试"),

    CREATE_CONTEST("create contest", "创建比赛");



    private final String type;

    private final String desc;



}
