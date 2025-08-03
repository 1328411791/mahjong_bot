package org.liahnu.bot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liahnu.bot.model.domain.User;

/**
 * @author lihanyu
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2025-08-03 19:56:33
 * @Entity org.liahnu.bot.model.domain.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




