package org.liahnu.bot.model.vo.contest;

import lombok.Data;

import java.util.Date;

/**
 * @author lihanyu
 */
@Data
public class UserLastContestVO {

    // 比赛 id
    private Long contestId;

    // 用户 id
    private Integer userId;

    // 结束点数
    private Integer endPoint;

    //  elo 变化
    private Integer eloChange;

    // 结束时间
    private Date time;
}
