package org.liahnu.bot.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 比赛结算
 * @TableName contest_end
 */
@TableName(value ="contest_end")
@Data
public class ContestEnd implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 比赛id
     */
    @TableField(value = "contest_id")
    private Integer contestId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 结算点数
     */
    @TableField(value = "end_point")
    private BigDecimal endPoint;

    // 积分变化
    @TableField(value = "elo_change")
    private BigDecimal eloChange;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}