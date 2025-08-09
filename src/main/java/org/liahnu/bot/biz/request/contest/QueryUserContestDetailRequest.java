package org.liahnu.bot.biz.request.contest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.liahnu.bot.biz.base.BizServiceBaseRequest;
import org.liahnu.bot.model.type.ContestType;


/**
 * @author lihanyu
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QueryUserContestDetailRequest extends BizServiceBaseRequest {

    // 用户 QQ 号
    @NotNull
    private Long userQqId;

    // 比赛类型
    private ContestType contestType;

    // 比赛数量
    @NotNull
    private Integer limit;

}
