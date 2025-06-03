package org.liahnu.bot.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.liahnu.bot.model.type.ContestType;

/**
 * elo表
 * @TableName elo
 */
@TableName(value ="elo")
@Data
public class Elo implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 
     */
    @TableField(value = "type")
    private ContestType type;

    /**
     * 
     */
    @TableField(value = "elo")
    private BigDecimal elo;

    /**
     * 
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}