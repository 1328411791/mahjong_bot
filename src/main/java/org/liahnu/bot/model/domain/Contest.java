package org.liahnu.bot.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.liahnu.bot.model.type.ContestStatus;
import org.liahnu.bot.model.type.ContestType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 比赛
 * @TableName contest
 */
@TableName(value ="contest")
@Data
public class Contest implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型
     */
    @TableField(value = "type")
    private ContestType type;

    /*
    *  状态
     */
    @TableField(value = "status")
     private ContestStatus status;

    /**
     * 
     */
    @TableField(value = "create_user_id")
    private Integer createUserId;

    /**
     * 
     */
    @TableField(value = "create_group_id")
    private Long createGroupId;

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