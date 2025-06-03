package org.liahnu.bot.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 比赛记录
 * @TableName contest_record
 */
@TableName(value ="contest_record")
@Data
public class ContestRecord implements Serializable {
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
     * direction
     */
    @TableField(value = "direction")
    private Integer direction;

    /**
     * 用户id
     */
    @TableField(value = "record_user_id")
    private Long recordUserId;

    /**
     * 点数
     */
    @TableField(value = "point")
    private BigDecimal point;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}