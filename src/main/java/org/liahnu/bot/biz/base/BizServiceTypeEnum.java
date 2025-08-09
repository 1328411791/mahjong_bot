package org.liahnu.bot.biz.base;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lihanyu
 */

@Getter
@AllArgsConstructor
public enum BizServiceTypeEnum {

    TEST("test", "测试"),

    CREATE_CONTEST("create contest", "创建比赛"),

    CREATE_CONTEST_RECORD("create record","创建记录" ),

    ADD_RECORD("add record", "添加记录"),

    QUERY_USER_CONTEST_DETAIL("query user contest detail", "查询用户比赛详情");



    private final String type;

    private final String desc;



}
