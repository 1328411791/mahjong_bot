package org.liahnu.bot.biz;


import jakarta.validation.constraints.NotNull;
import org.liahnu.bot.biz.base.BizServiceTypeEnum;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;


/*
 * @author lihanyu
 * @description 标记属于任务处理的Handler类
 */


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface BizServiceHandleInterface {

    // 任务类型
    @NotNull
    BizServiceTypeEnum type();

}
