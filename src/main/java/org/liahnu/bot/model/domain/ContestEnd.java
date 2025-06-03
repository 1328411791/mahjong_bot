package org.liahnu.bot.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 比赛结算
 * @TableName contest_end
 */
@TableName(value ="contest_end")
@Data
public class ContestEnd implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "contest_id")
    private Integer contestId;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 结算点数
     */
    @TableField(value = "end_point")
    private BigDecimal endPoint;

    // 积分变化
    @TableField(value = "elo_change")
    private BigDecimal eloChange;

    /**
     * 
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}