package org.liahnu.bot.biz.request.record;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.liahnu.bot.biz.base.BizServiceBaseRequest;
import org.liahnu.bot.model.type.DirectionType;


@Data
@EqualsAndHashCode(callSuper = true)
public class AddContestRecordBizServiceRequest extends BizServiceBaseRequest {

    /*
    * 比赛id
     */
    @NotNull
    private Integer contestId;

    /*
    * 玩家方向
     */
    @NotNull
    private DirectionType direction;

    /*
    * 玩家分数
     */
    @NotNull
    private Integer score;

    /*
     * 获取用户QQid
     */
    @NotNull
    private Long userId;

    /*
    * 获取群id
     */
    @NotNull
    private Long groupId;
}
