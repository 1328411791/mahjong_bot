package org.liahnu.bot.biz.request.contest;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.liahnu.bot.biz.base.BizServiceBaseRequest;
import org.liahnu.bot.model.type.ContestType;

@Data
public class CreateContestBizServiceRequest extends BizServiceBaseRequest {

    /*
    * 群ID
     */
    @NotNull
    private Long groupId;

    /*
    * 创建者ID
     */
    @NotNull
    private Long userId;

    /*
    * 比赛类型
     */
    @NotNull
    private ContestType contestType;
}
