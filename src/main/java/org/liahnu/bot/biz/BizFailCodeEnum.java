package org.liahnu.bot.biz;

public enum BizFailCodeEnum {

    // 默认业务异常
    BIZ_FAIL(1000, "业务异常"),

    // 参数异常
    PARAM_FAIL(1001, "参数异常"),


    /* 对局记录相关 */

    // 记录已存在
    CONTEST_RECORD_EXIST(1100, "该记录已存在"),

    ;

    final Integer code;

    final String description;

    BizFailCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
