package org.liahnu.bot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.liahnu.bot.model.domain.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
