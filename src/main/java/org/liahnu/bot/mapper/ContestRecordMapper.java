package org.liahnu.bot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.liahnu.bot.model.domain.ContestRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.liahnu.bot.model.vo.UserRecordVO;

import java.util.List;

/**
* @author li hanyu
* @description 针对表【contest_record(比赛记录)】的数据库操作Mapper
* @createDate 2025-06-03 16:29:58
* @Entity org.liahnu.bot.model.domain.ContestRecord
*/
@Mapper
public interface ContestRecordMapper extends BaseMapper<ContestRecord> {

    /*
    * 获取比赛中天添加的记录数
    * @param contestId
     */

    @Select("select count(*) from contest_record where contest_id = #{contestId}")
    Integer getContestRecordCount(Integer contestId);

    List<UserRecordVO> getUserRecord(Long userId, Integer limit);
}




