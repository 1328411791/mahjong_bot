package org.liahnu.bot.biz;

public enum BizFailCodeEnum {

    // 默认业务异常
    BIZ_FAIL(1000, "业务异常"),

    // 参数异常
    PARAM_FAIL(1001, "参数异常"),

    SYS_ERROR(1002, "系统异常"),
    ;

    final Integer code;

    final String description;

    BizFailCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
