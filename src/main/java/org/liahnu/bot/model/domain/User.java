package org.liahnu.bot.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 用户表
 * @author lihanyu
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * qq号
     */
    @TableField(value = "qq_id")
    private Long qqId;
    /**
     *
     */
    @TableField(value = "nick_name")
    private String nickname;
    /**
     * 扩展信息
     */
    @TableField(value = "ext_info", typeHandler = JacksonTypeHandler.class)
    private Map<String, String> extInfo;
    /**
     *
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     *
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}