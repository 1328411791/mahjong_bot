package org.liahnu.bot.model.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.liahnu.bot.model.type.ContestStatus;
import org.liahnu.bot.model.type.ContestType;
import org.liahnu.bot.model.type.DirectionType;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserRecordVO {

    /**
     * 类型
     */
    private ContestType type;

    /**
     *
     */
    private Long groupId;

    /**
     * direction
     */
    private DirectionType direction;

    /**
     * 点数
     */
    private Integer point;

    /**
     * 结算点数
     */
    private BigDecimal endPoint;

    // 积分变化
    private BigDecimal eloChange;

    // 时间
    private Date time;

}
