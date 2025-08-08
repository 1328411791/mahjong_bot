package org.liahnu.bot.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.liahnu.bot.model.type.ContestType;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * elo表
 * @TableName elo
 */
@TableName(value ="elo")
@Data
public class Elo implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 类型
     */
    @TableField(value = "type")
    private ContestType type;

    /**
     * elo
     */
    @TableField(value = "elo")
    private BigDecimal elo;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}