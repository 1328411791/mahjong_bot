package org.liahnu.bot.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.liahnu.bot.model.type.DirectionType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

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
    private DirectionType direction;

    /**
     * 用户id
     */
    @TableField(value = "record_user_id")
    private Integer recordUserId;

    /**
     * 点数
     */
    @TableField(value = "point")
    private Integer point;

    /**
     * 
     */
    @TableField(value = "create_time",fill =  FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_time",fill =  FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}