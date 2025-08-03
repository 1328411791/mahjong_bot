package org.liahnu.bot.model.domain;


import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
@TableName(value = "user")
public class User implements Serializable {

    /*
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /*
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /*
     * qqid
     */
    @TableField(value = "qq_id")
    private Long QQId;

    /*
     * 扩展信息
     */
    @TableField(value = "ext_info", typeHandler = JacksonTypeHandler.class)
    private Map<String, String> extInfo;

    /*
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /*
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
